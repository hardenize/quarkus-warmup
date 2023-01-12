package com.warmup.dao;

import com.warmup.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAll();

    Book findById(String id);

    boolean exists(String isbn);

    Book save(Book book);

    void update(Book book);

    void deleteById(String isbn);

}
