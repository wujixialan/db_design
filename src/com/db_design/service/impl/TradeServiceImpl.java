package com.db_design.service.impl;

import com.db_design.dao.TradeDao;
import com.db_design.dao.impl.TradeDaoImpl;
import com.db_design.service.TradeService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 17-1-8.
 */
public class TradeServiceImpl implements TradeService {
    private TradeDao tradeDao = new TradeDaoImpl();

    @Override
    public boolean add(String isbn, Integer quantity, double totalPrice) {
        return tradeDao.add(isbn, quantity, totalPrice);
    }

    @Override
    public List<String> getISBN() {
        return tradeDao.getISBN();
    }

    @Override
    public Map<String, Double> getTotalMonth(Date start, Date end, List<String> bookIsbn) {
        return tradeDao.getTotalMonth(start, end, bookIsbn);
    }
}
