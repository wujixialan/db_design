package com.db_design.model;

import java.sql.Date;

/**
 * Created by root on 17-1-6.
 */
public class Book {
//    图书的 isbn 号
    private String isbn;
//    书名
    private String bookName;
//    作者
    private String author;
//    进价
    private double price;
//    售价
    private double sale_price;
//    出版社
    private String publishing;
//    出版日期
    private Date publishDate;
//    库存量
    private int quantity;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book(String isbn, String bookName, String author, double price, double sale_price, String publishing, Date publishDate, int quantity, String supplier) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.sale_price = sale_price;
        this.publishing = publishing;
        this.publishDate = publishDate;
        this.quantity = quantity;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sale_price=" + sale_price +
                ", publishing=" + publishing +
                ", publishDate=" + publishDate +
                ", quantity=" + quantity +
                '}';
    }
}
