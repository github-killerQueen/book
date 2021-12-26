package com.guo.service;

import com.guo.bean.Book;
import com.guo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookMapper bookMapper;

    public Book selectById(Integer bookId) {
        Book book = bookMapper.selectById(bookId);
        return book;
    }

    public List<Book> selectAll() {
        List<Book> books = bookMapper.selectAll();
        return books;
    }

    public List<Book> selectByBorrowStudentId(Integer stuId) {
        List<Book> books = bookMapper.selectByBorrowStudentId(stuId);
        return books;
    }

    public void insertBook(Book book) {
        bookMapper.insertBook(book);
    }

    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    public void deleteBook(Integer bookId) {
        bookMapper.deleteBook(bookId);
    }

    public void updateBookStatus(String status, Integer bookId) {
        bookMapper.updateBookStatus(status, bookId);
    }
}
