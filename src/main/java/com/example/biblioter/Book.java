package com.example.biblioter;

import java.lang.reflect.Field;

public class Book {
    int id;
    String title;
    String author;
    String category;
    String borrowed;
    Book(){
        this.id=0;
        this.title="unset";
        this.author="unset";
        this.borrowed="unset";
        this.category="unset";
    }
    Book(int id, String title, String author, String category, String borrowed){
        this.id=id;
        this.title=title;
        this.author=author;
        this.borrowed=borrowed;
        this.category=category;
    }
    public static void showBook(Book book){
        System.out.println(book.getId());
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor());
        System.out.println(book.getCategory());
        System.out.println(book.getBorrowed());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(String borrowed) {
        this.borrowed = borrowed;
    }
}
