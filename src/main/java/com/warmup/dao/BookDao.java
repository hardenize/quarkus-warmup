package com.warmup.dao;

import com.warmup.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAll();

    Book findById(String id);

    Book save(Book book);
}
