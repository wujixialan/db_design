package com.db_design.model;

import java.sql.Date;

/**
 * Created by root on 17-1-20.
 */
public class Profit {
    private Integer id;
//    isbn
    private String isbn;
//    交易记录的 id
    private Integer trade_id;
//    利润 profit
    private double profit;
//    交易时间
    private Date trade_date;

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

    public Integer getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(Integer trade_id) {
        this.trade_id = trade_id;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public Date getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = trade_date;
    }

    public Profit(Integer id, String isbn, Integer trade_id, double profit, Date trade_date) {

        this.id = id;
        this.isbn = isbn;
        this.trade_id = trade_id;
        this.profit = profit;
        this.trade_date = trade_date;
    }

    public Profit() {

    }
}
