package com.warmup.dao;

import com.warmup.domain.Book;
import com.warmup.mapper.BookMapper;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@ApplicationScoped
public class BookDaoImpl implements BookDao {

    private final BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.selectAll();
    }

    @Override
    public Book findById(String id) {
        return bookMapper.selectById(id);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        List<Integer> authorIds = bookMapper.insertAuthors(book.getAuthors());
        List<Integer> editorIds = bookMapper.insertEditors(book.getEditors());
        List<Integer> reviewerIds = bookMapper.insertReviewers(book.getReviewers());

        bookMapper.insert(book);
        
        bookMapper.insertAuthorsForBook(authorIds, book.getIsbn());
        bookMapper.insertEditorsForBook(editorIds, book.getIsbn());
        bookMapper.insertReviewersForBook(reviewerIds, book.getIsbn());

        return bookMapper.selectById(book.getIsbn());

    }
}
