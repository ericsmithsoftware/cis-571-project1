package com.ericss.project1.api;

import com.ericss.project1.ws.GetBooksInfosByTitlesResponse;
import com.ericss.project1.service.BookInfoService;
import com.ericss.project1.ws.TitleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.ui.Model;
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

    @PayloadRoot(namespace = "http://project1.ericss.com/ws", localPart = "getBooksInfosByTitlesRequest")
    @ResponsePayload
    public GetBooksInfosByTitlesResponse getBooksInfosByTitles(@RequestPayload TitleList titleList, Model model) {
        GetBooksInfosByTitlesResponse response = bookInfoService.getBookInfoList(titleList.getTitles());
        model.addAttribute("bookInfoList", response);
        return response;
    }
}