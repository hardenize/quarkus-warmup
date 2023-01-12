package com.warmup.dao;

import com.warmup.domain.Review;

import java.util.List;

public interface ReviewDao {

    boolean exists(Integer id);

    List<Review> findAll();

    Review findById(Integer id);

    int save(Review review);

    void update(Review review);

    void deleteById(Integer id);

    void deleteForBook(String isbn);
}
