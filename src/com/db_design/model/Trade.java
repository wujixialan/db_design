package com.db_design.model;

import java.util.Date;

/**
 * Created by root on 17-1-6.
 */
public class Trade {
//    交易 id
    private Integer id;
//    图书的 isbn
    private String isbn;
//    交易时间
    private Date tradeDate;
//    用户 user_id
    private Integer user_id;
//    地址 address_id
    private Integer address_id;
//    交易数量
    private int quantity;
//    总钱数 total
    private double total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Trade(Integer id, String isbn, Date tradeDate, Integer user_id,
                 Integer address_id, int quantity, double total) {
        this.id = id;
        this.isbn = isbn;
        this.tradeDate = tradeDate;
        this.user_id = user_id;
        this.address_id = address_id;
        this.quantity = quantity;
        this.total = total;
    }

    public Trade() {
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", isbn=" + isbn +
                ", tradeDate=" + tradeDate +
                ", user_id=" + user_id +
                ", address_id=" + address_id +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
