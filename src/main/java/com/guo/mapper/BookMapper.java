package com.guo.mapper;

import com.guo.bean.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    public Book selectById(Integer bookId);

    public List<Book> selectAll();

    public List<Book> selectByBorrowStudentId(Integer stuId);

    public void insertBook(Book book);

    public void updateBook(Book book);

    public void deleteBook(Integer bookId);

    public void updateBookStatus(String status, Integer bookId);
}
