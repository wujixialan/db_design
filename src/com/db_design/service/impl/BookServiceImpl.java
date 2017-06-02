package com.db_design.service.impl;

import com.db_design.dao.BookDao;
import com.db_design.dao.impl.BookDaoImpl;
import com.db_design.model.Book;
import com.db_design.service.BookService;

import java.util.List;

/**
 * Created by root on 17-1-7.
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public boolean add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public boolean findByIsbn(String isbn) {
        return bookDao.findByIsbn(isbn);
    }

    @Override
    public boolean modifyQuantity(String isbn, int quantity) {
        return bookDao.modifyQuantity(isbn, quantity);
    }

    @Override
    public List<Book> findByBook() {
        return bookDao.findByBook();
    }

    @Override
    public int findByIsbnQuantity(String isbn) {
        return bookDao.findByIsbnQuantity(isbn);
    }

    @Override
    public double getByIsbnSalePrice(String isbn) {
        return bookDao.getByIsbnSalePrice(isbn);
    }

    @Override
    public List<Book> getByName(String text) {
        return bookDao.getByName(text);
    }
}
