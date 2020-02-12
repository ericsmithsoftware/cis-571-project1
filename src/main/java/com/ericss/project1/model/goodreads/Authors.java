package com.ericss.project1.model.goodreads;

import java.util.Arrays;

public class Authors {
    private Author[] author;

    public Author[] getAuthor ()
    {
        return author;
    }

    public void setAuthor (Author[] author)
    {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "author=" + Arrays.toString(author) +
                '}';
    }
}