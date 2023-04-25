package com.example.biblioter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnect {


    //Attributes

    public Connection connection;
    public static int booksAmount=0;


    //Constructor

    DBConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String username = "root";
        String password = "94541409MaR!";
        String url = "jdbc:mysql://localhost:3306/javadatabase";
        connection = DriverManager.getConnection(url, username, password);
    }


    //DB connection

    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    public void close() throws SQLException {
        connection.close();
    }


    //DB utilities

    private void downloadBook(ResultSet result, Book book) throws SQLException {
        book.setId(result.getInt("id"));
        book.setTitle(result.getString("title"));
        book.setAuthor(result.getString("author"));
        book.setCategory(result.getString("category"));
        book.setBorrowed(result.getString("borrowed"));
        book.setAccessible(result.getBoolean("accessibility"));
    }

    private void uploadBook(PreparedStatement statement, Book book) throws SQLException {
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getCategory());
        statement.setString(4, book.getBorrowed());
        statement.setBoolean(5,book.isAccessible());
        statement.executeUpdate();
    }


    //DB operations

    public Book getBook(int id) throws SQLException {
        Book book=new Book();
        PreparedStatement statement=connection.prepareStatement("SELECT * FROM bookstable WHERE id=?");
        statement.setInt(1,id);
        ResultSet result = statement.executeQuery();
        downloadBook(result, book);
        return book;
    }

    public Book getBook(String title) throws SQLException {
        Book book=new Book();
        PreparedStatement statement=connection.prepareStatement("SELECT * FROM bookstable WHERE title=?");
        statement.setString(1,title);
        ResultSet result = statement.executeQuery();
        downloadBook(result, book);
        return book;
    }

    public List<Book> getBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM bookstable");
        ResultSet result=statement.executeQuery();
        while(result.next()){
            Book book=new Book();
            downloadBook(result, book);
            books.add(book);
            booksAmount++;
        }
        return books;
    }

    public void addBook(Book book) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO bookstable (title, author, category, borrowed, accessibility) VALUES(?, ?, ?, ?, ?)");
        uploadBook(statement, book);
    }

    public void editBook(Book book) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("UPDATE bookstable SET title=?, author=?, category=?, borrowed=?, accessibility=? WHERE id=?");
        uploadBook(statement, book);
    }

    public void deleteBook(Book book) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("DELETE FROM bookstable WHERE id=?");
        statement.setInt(1,book.getId());
        statement.executeUpdate();
    }
}