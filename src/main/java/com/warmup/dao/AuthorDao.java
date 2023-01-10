package com.warmup.dao;

import com.warmup.domain.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> findAll();

    Author findById(Integer id);

    List<Author> findByFirstNameAndLastName(String firstName, String lastName);

    int save(Author author);
}
