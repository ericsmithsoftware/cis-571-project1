package com.ericss.project1.api;

import com.ericss.project1.exception.InternalErrorException;
import com.ericss.project1.model.rest.Book;
import com.ericss.project1.service.BookListService;
import com.ericss.project1.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookListController {

    private BookListService bookListService;

    @Autowired
    public BookListController(BookListService bookListService){
        this.bookListService = bookListService;
    }

    @GetMapping("/books/list.json")
    public ResponseEntity<List<Book>> getBookListAsJson(
            @RequestParam(name="numBooks", required=true) Integer numBooks,
            @RequestParam(name="listCategories", required=true) List<String> bestSellerCategories) {
        InputValidator.validateInput(numBooks, bestSellerCategories);
        List<Book> bookList;
        try{
            bookList = bookListService.getBookList(numBooks, bestSellerCategories);
        } catch(Exception e){
            throw new InternalErrorException(e.getMessage());
        }
        return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
    }

    @GetMapping("/books/list")
    public String getBookList(
            @RequestParam(name="numBooks", required=true) Integer numBooks,
            @RequestParam(name="listCategories", required=true) List<String> bestSellerCategories,
            Model model) {
        InputValidator.validateInput(numBooks, bestSellerCategories);
        List<Book> bookList;
        try{
            bookList = bookListService.getBookList(numBooks, bestSellerCategories);
        } catch(Exception e){
            throw new InternalErrorException(e.getMessage());
        }
        model.addAttribute("bookList", bookList);
        return "bookList";
    }
}