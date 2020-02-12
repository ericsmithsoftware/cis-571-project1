package com.ericss.project1.mapper;

import com.ericss.project1.model.goodreads.Author;
import com.ericss.project1.model.goodreads.Authors;
import com.ericss.project1.model.goodreads.Book;
import com.ericss.project1.model.goodreads.GoodreadsResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BookInfoListMapperTest {

    private static List<GoodreadsResponse> goodreadsResponseList;

    @BeforeAll
    public static void setup(){
        goodreadsResponseList = new ArrayList<>();
        GoodreadsResponse goodreadsResponse = new GoodreadsResponse();
        Book book = new Book();
        book.setTitle("Title");
        Authors authors = new Authors();
        Author author = new Author();
        author.setName("Author");
        authors.setAuthor(new Author[]{author});
        book.setAuthors(authors);
        book.setDescription("description");
        goodreadsResponse.setBook(book);

        goodreadsResponseList.add(goodreadsResponse);
    }


    @Test
    public void test(){
        BookInfoListMapper.map(goodreadsResponseList);
    }
}