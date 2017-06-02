package com.db_design.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 17-1-8.
 */
public interface TradeDao {
    boolean add(String isbn, Integer quantity, double totalPrice);

    List<String> getISBN();

    Map<String,Double> getTotalMonth(Date start, Date end, List<String> bookIsbn);
}
