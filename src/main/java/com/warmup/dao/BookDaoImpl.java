package com.warmup.dao;

import com.warmup.domain.Book;
import com.warmup.mapper.BookMapper;
import com.warmup.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@ApplicationScoped
public class BookDaoImpl implements BookDao {

    private final BookMapper bookMapper;
    private final ReviewMapper reviewMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.selectAll();
    }

    @Override
    public Book findById(String id) {
        return bookMapper.selectById(id);
    }

    @Override
    public boolean exists(String isbn) {
        return bookMapper.existsByIsbn(isbn);
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
        reviewMapper.insertReviews(book.getReviews());

        return bookMapper.selectById(book.getIsbn());
    }

    @Override
    public void update(Book book) {
        bookMapper.update(book);
    }

    @Override
    public void deleteById(String isbn) {
        bookMapper.deletePeopleByBookId(isbn);
        reviewMapper.deleteForBook(isbn);
        bookMapper.deleteById(isbn);
    }
}
