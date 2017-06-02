package com.db_design.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 17-1-21.
 */
public interface ProfitDao {
    Map<String, Double> getProfit(Date start, Date end, List<String> bookIsbn);

    Double getProfit(Date start, Date end);

    List<String> getISBN();
}
