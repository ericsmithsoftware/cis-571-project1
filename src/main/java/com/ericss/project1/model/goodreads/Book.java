package com.ericss.project1.model.goodreads;

public class Book {

    private String language_code;
    private String image_url;
    private String isbn;
    private String isbn13;
    private String num_pages;
    private String publisher;
    private String description;
    private String average_rating;
    private String title;
    private Authors authors;

    public String getLanguage_code ()
    {
        return language_code;
    }

    public void setLanguage_code (String language_code)
    {
        this.language_code = language_code;
    }

    public String getImage_url ()
    {
        return image_url;
    }

    public void setImage_url (String image_url)
    {
        this.image_url = image_url;
    }

    public String getIsbn ()
    {
        return isbn;
    }

    public void setIsbn (String isbn)
    {
        this.isbn = isbn;
    }

    public String getIsbn13 ()
    {
        return isbn13;
    }

    public void setIsbn13 (String isbn13)
    {
        this.isbn13 = isbn13;
    }

    public String getNum_pages ()
    {
        return num_pages;
    }

    public void setNum_pages (String num_pages)
    {
        this.num_pages = num_pages;
    }

    public String getPublisher ()
    {
        return publisher;
    }

    public void setPublisher (String publisher)
    {
        this.publisher = publisher;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getAverage_rating ()
    {
        return average_rating;
    }

    public void setAverage_rating (String average_rating)
    {
        this.average_rating = average_rating;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public Authors getAuthors ()
    {
        return authors;
    }

    public void setAuthors (Authors authors)
    {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "language_code='" + language_code + '\'' +
                ", image_url='" + image_url + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", num_pages='" + num_pages + '\'' +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", average_rating='" + average_rating + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                '}';
    }
}