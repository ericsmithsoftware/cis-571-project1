package com.ericss.project1.service;

import com.ericss.project1.invoker.NewYorkTimesInvoker;
import com.ericss.project1.mapper.BestSellerListMapper;
import com.ericss.project1.model.nytimes.BestSellerList;
import com.ericss.project1.model.rest.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookListService.class);

    private NewYorkTimesInvoker newYorkTimesInvoker;

    @Autowired
    public BookListService(NewYorkTimesInvoker newYorkTimesInvoker){
        this.newYorkTimesInvoker = newYorkTimesInvoker;
    }

    public List<Book> getBookList(Integer numBooks, List<String> listNames) {
        List<BestSellerList> bestSellerResultLists = getBestSellerListResults(listNames);
        numBooks = updateNumBooksIfNotEnough(numBooks, bestSellerResultLists);
        return createBookListFromResults(numBooks, bestSellerResultLists);
    }

    private List<Book> createBookListFromResults(Integer numBooks, List<BestSellerList> bestSellerResultLists) {
        List<Book> bookList = new ArrayList<>();
        int numLists = bestSellerResultLists.size();
        int i = 0;
        int bookIndex = 0;
        while(i < numBooks){
            int listIndex = i%numLists;
            bookList.add(getBook(bestSellerResultLists.get(i%numLists), bookIndex));
            i++;
            if(listIndex == numLists-1){
                bookIndex++;
            }
        }
        return bookList;
    }

    private Integer updateNumBooksIfNotEnough(Integer numBooks, List<BestSellerList> bestSellerResultLists) {
        int resultsFound=0;
        for(BestSellerList list: bestSellerResultLists){
            resultsFound+= list.getResults().getBooks().size();
        }
        LOGGER.debug("Num results: {}" +resultsFound);
        if(numBooks > resultsFound){
            numBooks = resultsFound;
        }
        return numBooks;
    }

    private Book getBook(BestSellerList bestSellerList, int bookIndex) {
        com.ericss.project1.model.nytimes.Book listBook =
                bestSellerList.getResults().getBooks().get(bookIndex);
        return new Book(listBook.getTitle(), listBook.getAuthor());
    }

    private List<BestSellerList> getBestSellerListResults(List<String> listNames) {
        List<BestSellerList> bestSellerResultLists = new ArrayList<>();
        for(String listName : listNames){
            LOGGER.debug("Category: {}", listName);
            ResponseEntity<String> responseEntity = newYorkTimesInvoker.getBestSellerList(listName);
            BestSellerList bestSellerList = BestSellerListMapper.map(responseEntity);
            if(null != bestSellerList){
                LOGGER.debug("BestSellerList: {}", bestSellerList.toString());
                bestSellerResultLists.add(bestSellerList);
            }
        }
        return bestSellerResultLists;
    }
}