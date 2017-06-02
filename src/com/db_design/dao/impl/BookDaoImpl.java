package com.db_design.dao.impl;

import com.db_design.dao.BookDao;
import com.db_design.model.Book;
import com.db_design.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 17-1-7.
 */
public class BookDaoImpl implements BookDao {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public boolean add(Book book) {
        try {
            System.out.println("book: " + book);
            connection = DBUtils.getConnection();
            String sql = "insert into book(" +
                    "isbn, book_name, author, price, sale_price, publishing, publish_date, quantity) values(" +
                    "'" + book.getIsbn() + "', "
                    + "'" + book.getBookName() + "', "
                    + "'" + book.getAuthor() + "', "
                    + "" + book.getPrice() + ", "
                    + "" + book.getSale_price() + ", "
                    + "'" + book.getPublishing() + "', "
                    + "'" + book.getPublishDate() + "', "
                    + "" + book.getQuantity() + ")";
            ps = DBUtils.getPs(connection, sql);
            int flag = ps.executeUpdate();
            if(flag == 0) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return false;
    }

    @Override
    public boolean findByIsbn(String isbn) {
        int i = 0;
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from book where isbn = " + isbn;
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                i++;
            }
            if(i == 0) {
                return false;
            } else if(i > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return false;
    }

    @Override
    public boolean modifyQuantity(String isbn, int quantity) {
        try {
            connection = DBUtils.getConnection();
            String sql = "update book set quantity = + " + quantity + " where isbn = " + isbn;
            ps = DBUtils.getPs(connection, sql);
            int flag = ps.executeUpdate();
            if(flag == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return false;
    }

    @Override
    public List<Book> findByBook() {
        List<Book> books = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from book";
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setBookName(rs.getString("book_name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setSale_price(rs.getDouble("sale_price"));
                book.setPublishing(rs.getString("publishing"));
                book.setPublishDate(rs.getDate("publish_date"));
                book.setQuantity(rs.getInt("quantity"));

                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return books;
    }

    @Override
    public int findByIsbnQuantity(String isbn) {
        int i = 0;
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from book where isbn = " + isbn;
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                i = rs.getInt("quantity");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return i;
    }

    @Override
    public double getByIsbnSalePrice(String isbn) {
        double price = 0.0;
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from book where isbn = " + isbn;
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                price = rs.getInt("sale_price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return price;
    }

    @Override
    public List<Book> getByName(String text) {
        List<Book> books = new ArrayList<Book>();
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from book where book_name like '%" + text + "%'";
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setBookName(rs.getString("book_name"));
                book.setAuthor(rs.getString("author"));
                book.setSale_price(rs.getDouble("sale_price"));
                book.setPublishing(rs.getString("publishing"));
                book.setPublishDate(rs.getDate("publish_date"));
                book.setQuantity(rs.getInt("quantity"));

                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return books;
    }
}
