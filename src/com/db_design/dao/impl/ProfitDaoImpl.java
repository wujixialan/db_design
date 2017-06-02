package com.db_design.dao.impl;

import com.db_design.dao.ProfitDao;
import com.db_design.utils.DBUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 17-1-21.
 */
public class ProfitDaoImpl implements ProfitDao {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public Map<String, Double> getProfit(Date start, Date end, List<String> bookIsbn) {
        Map<String, Double> profit = new HashMap<>();
        try {
            connection = DBUtils.getConnection();
            for (int i = 0; i < bookIsbn.size(); i++) {
                String sql = "select sum(profit) as profit, book_isbn from profit " +
                        "where trade_date between '" + start + "'"
                        + " and '" + end + "' and book_isbn = '" + bookIsbn.get(i) + "'";
                ps = DBUtils.getPs(connection, sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    profit.put(rs.getString("book_isbn"), rs.getDouble("profit"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                DBUtils.close(rs);
            }
            if (ps != null) {
                DBUtils.close(ps);
            }
            if (connection != null) {
                DBUtils.close(connection);
            }
        }
        return profit;
    }

    @Override
    public Double getProfit(Date start, Date end) {
        double profit = 0.0;
        try {
            connection = DBUtils.getConnection();
            String sql = "select sum(profit) as profit from profit " +
                    "where trade_date between '" + start + "'"
                    + " and '" + end + "'";
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                profit = rs.getDouble("profit");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                DBUtils.close(rs);
            }
            if (ps != null) {
                DBUtils.close(ps);
            }
            if (connection != null) {
                DBUtils.close(connection);
            }
        }
        return profit;
    }

    @Override
    public List<String> getISBN() {
        List<String> isbn = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from profit";
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                isbn.add(rs.getString("book_isbn"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                DBUtils.close(rs);
            }
            if (ps != null) {
                DBUtils.close(ps);
            }
            if (connection != null) {
                DBUtils.close(connection);
            }
        }
        return isbn;
    }
}
