package com.guo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guo.bean.Book;
import com.guo.bean.Borrow;
import com.guo.service.BookService;
import com.guo.service.BorrowService;
import com.guo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BorrowController {

    @Autowired
    BorrowService borrowService;

    @Autowired
    BookService bookService;

    @Autowired
    StudentService studentService;

    @GetMapping("/selectBorrow/{bookId}")
    @ResponseBody
    public Borrow getBorrowByBookId(@PathVariable("bookId") Integer bookId) {
        Borrow borrow = borrowService.selectByBookId(bookId);
        return borrow;
    }

    @GetMapping("/selectBorrows")
    @ResponseBody
    public PageInfo getBorrowList(@RequestParam(value = "pn",defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 8);
        List<Borrow> borrows = borrowService.selectAll();
        PageInfo pi = new PageInfo(borrows);
        return pi;
    }

    @PutMapping("/updateBorrow/{bookId}")
    @ResponseBody
    public String updateBorrow(Borrow borrow) {
        borrowService.updateBorrow(borrow);
        return null;
    }

    @PostMapping("/insertBorrow")
    @ResponseBody
    @Transactional
    public String insertBorrow(Borrow borrow) {
        borrowService.insertBorrow(borrow);
        bookService.updateBookStatus("已借出", borrow.getBookId());
        studentService.updateStudentCountAdd(borrow.getStuId());
        return null;
    }

    @DeleteMapping("/deleteBorrow")
    @ResponseBody
    @Transactional
    public String deleteBorrow(@RequestParam("bookId") Integer bookId,
                               @RequestParam("stuId") Integer stuId) {
        borrowService.deleteBorrow(bookId);
        bookService.updateBookStatus("未借出", bookId);
        studentService.updateStudentCountReduce(stuId);
        return null;
    }

    @GetMapping("/selectStudentBorrows")
    @ResponseBody
    public PageInfo getStudentBorrows(@RequestParam(value = "pn",defaultValue = "1") Integer pn,
                                      @RequestParam("stuId") Integer stuId) {
        PageHelper.startPage(pn, 8);
        List<Book> books = bookService.selectByBorrowStudentId(stuId);
        PageInfo pi = new PageInfo(books);
        return pi;
    }
}
