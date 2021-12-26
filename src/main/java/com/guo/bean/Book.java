package com.guo.bean;

public class Book {
    private Integer bookId;
    private String bookName;
    private String author;
    private String press;
    private String status;

    public Book() {}

    public Book(Integer bookId, String bookName, String author, String press, String status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.press = press;
        this.status = status;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
