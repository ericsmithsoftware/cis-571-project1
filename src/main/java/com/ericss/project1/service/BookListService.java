package com.ericss.project1.service;

import com.ericss.project1.invoker.NewYorkTimesInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookListService {

    private NewYorkTimesInvoker newYorkTimesInvoker;

    @Autowired
    public BookListService(NewYorkTimesInvoker newYorkTimesInvoker){
        this.newYorkTimesInvoker = newYorkTimesInvoker;
    }

    @Value("${ny.times.path.list.names}")
    private String bestSellerList;

    public ResponseEntity<String> getBookList(Integer numBooks) {
        return newYorkTimesInvoker.getBestSellerList(bestSellerList);
    }
}