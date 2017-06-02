package com.db_design.dao.impl;

import com.db_design.dao.TradeDao;
import com.db_design.listener.MenuListener;
import com.db_design.ui.SaleBook;
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
 * Created by root on 17-1-8.
 */
public class TradeDaoImpl implements TradeDao {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public boolean add(String isbn, Integer quantity, double totalPrice) {
        boolean flag = false;
        try {
            connection = DBUtils.getConnection();
            int total = MenuListener.bookService.findByIsbnQuantity(isbn);
            flag = MenuListener.bookService.modifyQuantity(isbn, (total - quantity));
            if (flag) {
                Date date = new Date(new java.util.Date().getTime());
                String sql = "insert into trade(isbn, trade_date, user_id, quantity, total)" +
                        " values(" +
                        "'" + isbn + "', "
                        + "'" + date + "', "
                        + SaleBook.vipNo + ", "
                        + quantity + ", "
                        + totalPrice + ")";
                ps = DBUtils.getPs(connection, sql);
                int flag1 = ps.executeUpdate();
                if (flag1 != 0) {
                    sql = "select price, id from book b, trade t where b.isbn = t.isbn and b.isbn = " + isbn;
                    ps = DBUtils.getPs(connection, sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Integer id = rs.getInt("id");
                        Double price = rs.getDouble("price");
                        sql = "insert into profit(book_isbn, trade_id, profit, trade_date)  values('" + isbn + "', " + id + ", " + (totalPrice - (price * quantity)) + ", '" +  date + "')";
                        ps = DBUtils.getPs(connection, sql);
                        flag1 = ps.executeUpdate();
                        if(flag1 != 0) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else {
                return false;
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

        return false;
    }

    @Override
    public List<String> getISBN() {
        List<String> isbn = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from trade";
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                isbn.add(rs.getString("isbn"));
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

    @Override
    public Map<String, Double> getTotalMonth(Date start, Date end, List<String> bookIsbn) {
        Map<String, Double> totalMonth = new HashMap<>();
        try {
            connection = DBUtils.getConnection();
            for (int i = 0; i < bookIsbn.size(); i++) {
                String sql = "select sum(total) as total, isbn from trade " +
                        "where trade_date between '" + start + "'"
                        + " and '" + end + "' and isbn = '" + bookIsbn.get(i) + "'";
                ps = DBUtils.getPs(connection, sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    totalMonth.put(rs.getString("isbn"), rs.getDouble("total"));
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
        return totalMonth;
    }
}
