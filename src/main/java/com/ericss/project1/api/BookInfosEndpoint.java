package com.ericss.project1.api;

import com.ericss.project1.soap.GetBooksInfosByTitlesResponse;
import com.ericss.project1.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class BookInfosEndpoint {

    private static final String NAMESPACE_URI = "http://project1.ericss.com/soap";

    private BookInfoService bookInfoService;

    @Autowired
    public BookInfosEndpoint(BookInfoService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBooksInfosByTitlesRequest")
    @ResponsePayload
    public GetBooksInfosByTitlesResponse getBooksInfosByTitles(@RequestPayload List<String> titles) {
        GetBooksInfosByTitlesResponse response = bookInfoService.getBookInfoList(titles);
        return response;
    }
}