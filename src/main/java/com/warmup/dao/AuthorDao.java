package com.warmup.dao;

import com.warmup.domain.Author;
import com.warmup.domain.Person;

import java.util.List;
import java.util.Map;

public interface AuthorDao {

    boolean exists(Integer id);

    List<Author> findAll();

    Map<String, List<Person>> findAllGrouped();

    Author findById(Integer id);

    List<Author> findByFirstNameAndLastName(String firstName, String lastName);

    int save(Author author);

    void update(Author author);

    void deleteForBookByAuthorId(String isbn, Integer authorId);

}
