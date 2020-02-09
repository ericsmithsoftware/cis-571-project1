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
        List<Book> bookList = new ArrayList<>();
        List<BestSellerList> bestSellerResultLists = populateBestSellerListResults(listNames);
        int numLists = bestSellerResultLists.size();
        int i = 0;
        int bookIndex = 0;
        while(i < numBooks){
            int listIndex = i%numLists;
            LOGGER.debug("List: {} - {}", listIndex, bookIndex);
            bookList.add(getBook(bestSellerResultLists.get(i%numLists), bookIndex));
            i++;
            if(listIndex == numLists-1){
                bookIndex++;
            }
        }
        return bookList;
    }

    private Book getBook(BestSellerList bestSellerList, int bookIndex) {
        com.ericss.project1.model.nytimes.Book listBook =
                bestSellerList.getResults().getBooks().get(bookIndex);
        return new Book(listBook.getTitle(), listBook.getAuthor());
    }

    private List<BestSellerList> populateBestSellerListResults(List<String> listNames) {
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