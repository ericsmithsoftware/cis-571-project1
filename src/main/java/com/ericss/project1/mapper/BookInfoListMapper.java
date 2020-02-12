package com.ericss.project1.mapper;

import com.ericss.project1.model.goodreads.GoodreadsResponse;
import com.ericss.project1.bookinfo.Book;
import com.ericss.project1.bookinfo.BookList;
import com.ericss.project1.bookinfo.GetBooksInfosByTitlesResponse;
import com.ericss.project1.bookinfo.ObjectFactory;

import java.util.List;

public final class BookInfoListMapper {

    private static ObjectFactory objectFactory;

    public BookInfoListMapper(){
        this.objectFactory = new ObjectFactory();
    }

    public static GetBooksInfosByTitlesResponse map(List<GoodreadsResponse> goodreadsResponseList) {
        GetBooksInfosByTitlesResponse getBooksInfosByTitlesResponse =
                objectFactory.createGetBooksInfosByTitlesResponse();

        BookList bookList = objectFactory.createBookList();
        if(null != goodreadsResponseList && goodreadsResponseList.size() > 0){
            mapGoodReadsBooksAndAddToBookList(goodreadsResponseList, bookList);
        }
        getBooksInfosByTitlesResponse.setBookList(bookList);

       return getBooksInfosByTitlesResponse;
    }

    private static void mapGoodReadsBooksAndAddToBookList(List<GoodreadsResponse> goodreadsResponseList, BookList bookList) {
        for(GoodreadsResponse goodreadsResponse : goodreadsResponseList){
            if(null != goodreadsResponse.getBook()){
                com.ericss.project1.model.goodreads.Book goodReadsBook = goodreadsResponse.getBook();
                Book book = createBook(goodReadsBook);
                bookList.getBookList().add(book);
            }
        }
    }

    private static Book createBook(com.ericss.project1.model.goodreads.Book goodReadsBook){
        Book book = objectFactory.createBook();

        String author = null;
        if(null != goodReadsBook.getAuthors()
                && 0 < goodReadsBook.getAuthors().getAuthor().length){
            author = goodReadsBook.getAuthors().getAuthor()[0].getName();
        }

        book.setTitle(goodReadsBook.getTitle());
        book.setAuthor(author);
        book.setDescription(goodReadsBook.getDescription());
        book.setRating(goodReadsBook.getAverage_rating());

        return book;
    }
}