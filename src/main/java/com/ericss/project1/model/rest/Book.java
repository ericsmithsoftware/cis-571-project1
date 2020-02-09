package com.ericss.project1.model.rest;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = -957356133850899072L;

    /**
     * @param title
     * @param author
     **/
    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}