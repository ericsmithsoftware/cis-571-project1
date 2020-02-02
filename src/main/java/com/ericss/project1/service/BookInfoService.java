package com.ericss.project1.service;

import com.ericss.project1.invoker.GoodreadsInvoker;
import com.ericss.project1.model.booksinfos.Book;
import com.ericss.project1.model.booksinfos.GetBooksInfosByTitlesResponse;
import com.ericss.project1.model.booksinfos.ObjectFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookInfoService {

    private GoodreadsInvoker goodreadsInvoker;
    private ObjectFactory objectFactory;

    public BookInfoService(GoodreadsInvoker goodreadsInvoker){
        this.goodreadsInvoker = goodreadsInvoker;
        this.objectFactory = new ObjectFactory();
    }

    public GetBooksInfosByTitlesResponse getBookInfoList(List<String> titles) {
        for(String title : titles){
            goodreadsInvoker.getBookReview(title);
        }
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        bookList.add(book);

        return objectFactory.createGetBooksInfosByTitlesResponse();
    }

}