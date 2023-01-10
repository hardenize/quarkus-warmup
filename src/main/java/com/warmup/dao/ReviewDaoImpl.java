package com.warmup.dao;

import com.warmup.domain.Review;
import com.warmup.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@RequiredArgsConstructor
@ApplicationScoped
public class ReviewDaoImpl implements ReviewDao {

    private final ReviewMapper reviewMapper;

    @Override
    public List<Review> findAll() {
        return reviewMapper.selectAll();
    }

    @Override
    public Review findById(Integer id) {
        return reviewMapper.selectById(id);
    }
}
