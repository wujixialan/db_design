package com.db_design.service;

import com.db_design.model.Book;

import java.util.List;

/**
 * Created by root on 17-1-7.
 */
public interface BookService {
    boolean add(Book book);

    boolean findByIsbn(String isbn);

    boolean modifyQuantity(String isbn, int quantity);

    List<Book> findByBook();

    int findByIsbnQuantity(String isbn);

    double getByIsbnSalePrice(String isbn);

    List<Book> getByName(String text);
}
