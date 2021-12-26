package com.guo.service;

import com.guo.bean.Borrow;
import com.guo.mapper.BookMapper;
import com.guo.mapper.BorrowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {

    @Autowired
    BorrowMapper borrowMapper;

    public Borrow selectByBookId(Integer bookId) {
        Borrow borrow = borrowMapper.selectByBookId(bookId);
        return borrow;
    }

    public Borrow selectByStudentId(Integer stuId) {
        Borrow borrow = borrowMapper.selectByStudentId(stuId);
        return borrow;
    }

    public List<Borrow> selectAll() {
        List<Borrow> borrows = borrowMapper.selectAll();
        return borrows;
    }

    public void updateBorrow(Borrow borrow) {
        borrowMapper.updateBorrow(borrow);
    }

    public void insertBorrow(Borrow borrow) {
        borrowMapper.insertBorrow(borrow);
    }

    public void deleteBorrow(Integer bookId) {
        borrowMapper.deleteBorrow(bookId);
    }
}
