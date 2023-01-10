package com.warmup.dao;

import com.warmup.domain.Review;

import java.util.List;

public interface ReviewDao {

    List<Review> findAll();


    Review findById(Integer id);
}
