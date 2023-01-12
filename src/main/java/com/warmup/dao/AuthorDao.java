package com.warmup.dao;

import com.warmup.domain.Author;

import java.util.List;

public interface AuthorDao {

    boolean exists(Integer id);

    List<Author> findAll();

    Author findById(Integer id);

    List<Author> findByFirstNameAndLastName(String firstName, String lastName);

    int save(Author author);

    void update(Author author);

    void deleteForBookByAuthorId(String isbn, Integer authorId);

}
