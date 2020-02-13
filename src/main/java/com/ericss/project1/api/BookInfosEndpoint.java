package com.ericss.project1.api;

import com.ericss.project1.bookinfo.GetBooksInfosByTitlesRequest;
import com.ericss.project1.bookinfo.GetBooksInfosByTitlesResponse;
import com.ericss.project1.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BookInfosEndpoint {

    private BookInfoService bookInfoService;

    @Autowired
    public BookInfosEndpoint(BookInfoService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }

    @PayloadRoot(namespace = "http://project1.ericss.com/bookInfo", localPart = "GetBooksInfosByTitlesRequest")
    @ResponsePayload
    public GetBooksInfosByTitlesResponse getBooksInfosByTitlesRequest(@RequestPayload GetBooksInfosByTitlesRequest request) {
        GetBooksInfosByTitlesResponse response = bookInfoService.getBookInfoList(request.getTitleList().getTitle());
        return response;
    }
}