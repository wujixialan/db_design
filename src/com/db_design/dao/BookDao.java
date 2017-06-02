package com.db_design.dao;

import com.db_design.model.Book;

import java.util.List;

/**
 * Created by root on 17-1-7.
 */
public interface BookDao {
//    录入书籍
    boolean add(Book book);

//    通过 isbn 号，查询
    boolean findByIsbn(String isbn);

//    更新库存量
    boolean modifyQuantity(String isbn, int quantity);

    List<Book> findByBook();

    int findByIsbnQuantity(String isbn);

    double getByIsbnSalePrice(String isbn);

    List<Book> getByName(String text);
}
