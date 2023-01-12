package com.warmup.dao;

import com.warmup.domain.Author;
import com.warmup.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@RequiredArgsConstructor
@ApplicationScoped
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorMapper authorMapper;

    @Override
    public boolean exists(Integer id) {
        return authorMapper.existsById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorMapper.selectAll();
    }

    @Override
    public Author findById(Integer id) {
        return authorMapper.selectById(id);
    }

    @Override
    public List<Author> findByFirstNameAndLastName(String firstName, String lastName) {
        return authorMapper.selectByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public int save(Author author) {
        return authorMapper.insertAuthor(author);
    }

    @Override
    public void update(Author author) {
        authorMapper.updateAuthor(author);
    }

    @Override
    public void deleteForBookByAuthorId(String isbn, Integer authorId) {
        authorMapper.deleteForBookByAuthorId(isbn, authorId);
    }

}
