package com.ericss.cis571project1.api;

import com.ericss.cis571project1.service.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookListController {

    private BookListService bookListService;

    @Autowired
    public BookListController(BookListService bookListService){
        this.bookListService = bookListService;
    }

    @GetMapping("/books/list")
    public ResponseEntity<String> getBookList(@RequestParam(name="numBooks", required=true)
                            Integer numBooks, Model model) {
        ResponseEntity<String> bestSellerList = bookListService.getBookList(numBooks);
        model.addAttribute("bestSellerList", bestSellerList);
        return bestSellerList;
    }

}