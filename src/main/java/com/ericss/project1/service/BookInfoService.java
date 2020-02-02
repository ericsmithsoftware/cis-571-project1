package com.ericss.project1.service;

import com.ericss.project1.invoker.GoodreadsInvoker;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookInfoService {

    private GoodreadsInvoker goodreadsInvoker;

    public BookInfoService(GoodreadsInvoker goodreadsInvoker){
        this.goodreadsInvoker = goodreadsInvoker;
    }

    public List<String> getBookInfoList(List<String> titles) {
        for(String title : titles){
            goodreadsInvoker.getBookReview(title);
        }
        return null;
    }

}