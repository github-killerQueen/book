package com.guo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guo.bean.Book;
import com.guo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/people/ihome4")
    @ResponseBody
    public Object w(String pageindex) {
        Map map = new HashMap();
        map.put("1",pageindex);
        return map;
    }

    @GetMapping("/w")
    public String tow() {
        return "w";
    }



    @GetMapping("/selectBook/{bookId}")
    @ResponseBody
    public Book getBook(@PathVariable("bookId") Integer bookId) {
        Book book = bookService.selectById(bookId);
        return book;
    }

    @GetMapping("/selectBooks")
    @ResponseBody
    public PageInfo getBookList(@RequestParam(value = "pn",defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 8);
        List<Book> books = bookService.selectAll();
        PageInfo pi = new PageInfo(books);
        return pi;
    }

    @PostMapping("/addBook")
    @ResponseBody
    public String insertBook(Book book) {
        bookService.insertBook(book);
        return null;
    }

    @PutMapping("updateBook/{bookId}")
    @ResponseBody
    public String updateBook(Book book) {
        bookService.updateBook(book);
        return null;
    }


    @DeleteMapping("/deleteBook/{bookId}")
    @ResponseBody
    public String deleteBook(@PathVariable("bookId") Integer bookId) {
        bookService.deleteBook(bookId);
        return null;
    }
}
