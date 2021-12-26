package com.guo.bean;

import java.sql.Date;

public class Borrow {
    private Integer bookId;
    private String bookName;
    private Integer StuId;
    private String StuName;
    private Date borrowDate;
    private Date returnDate;

    public Borrow() {}

    public Borrow(Integer bookId, String bookName, Integer stuId, String stuName, Date borrowDate, Date returnDate) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.StuId = stuId;
        this.StuName = stuName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getStuId() {
        return StuId;
    }

    public void setStuId(Integer stuId) {
        StuId = stuId;
    }

    public String getStuName() {
        return StuName;
    }

    public void setStuName(String stuName) {
        StuName = stuName;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", StuId=" + StuId +
                ", StuName='" + StuName + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
