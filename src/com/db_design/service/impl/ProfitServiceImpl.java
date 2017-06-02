package com.db_design.service.impl;

import com.db_design.dao.ProfitDao;
import com.db_design.dao.impl.ProfitDaoImpl;
import com.db_design.service.ProfitService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 17-1-21.
 */
public class ProfitServiceImpl implements ProfitService {
    private ProfitDao profitDao = new ProfitDaoImpl();

    @Override
    public Map<String, Double> getProfit(Date start, Date end, List<String> bookIsbn) {
        return profitDao.getProfit(start, end, bookIsbn);
    }

    @Override
    public Double getProfit(Date start, Date end) {
        return profitDao.getProfit(start, end);
    }

    @Override
    public List<String> getISBN() {
        return profitDao.getISBN();
    }
}
