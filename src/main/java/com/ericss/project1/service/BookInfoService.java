package com.ericss.project1.service;

import com.ericss.project1.invoker.GoodreadsInvoker;
import com.ericss.project1.mapper.BookInfoListMapper;
import com.ericss.project1.mapper.GoodreadsResponseUnmarshaller;
import com.ericss.project1.model.goodreads.GoodreadsResponse;
import com.ericss.project1.bookinfo.GetBooksInfosByTitlesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookInfoService {

    private GoodreadsInvoker goodreadsInvoker;

    public BookInfoService(GoodreadsInvoker goodreadsInvoker){
        this.goodreadsInvoker = goodreadsInvoker;
    }

    public GetBooksInfosByTitlesResponse getBookInfoList(List<String> titleList) {
        List<GoodreadsResponse> goodreadsResponseList = new ArrayList<>();
        /*for(String title : titles){
            ResponseEntity<String> responseEntity = goodreadsInvoker.getBookReview(title);
            goodreadsResponseList.add(GoodreadsResponseUnmarshaller.unmarshal(responseEntity));
        }*/

        ResponseEntity<String> responseEntity = goodreadsInvoker.getBookReview(titleList.get(0));
        GoodreadsResponse goodreadsResponse = GoodreadsResponseUnmarshaller.unmarshal(responseEntity);
        if(null != goodreadsResponse){
            goodreadsResponseList.add(goodreadsResponse);
        }

        return BookInfoListMapper.map(goodreadsResponseList);
    }

}