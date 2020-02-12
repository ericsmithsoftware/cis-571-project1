package com.ericss.project1.model.goodreads;

public class GoodreadsResponse {

    private Book book;

    public Book getBook ()
    {
        return book;
    }

    public void setBook (Book book)
    {
        this.book = book;
    }

    @Override
    public String toString() {
        return "GoodreadsResponse{book=" + book + '}';
    }
}