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
    public boolean exists(Integer id) {
        return reviewMapper.existsById(id);
    }
    @Override
    public List<Review> findAll() {
        return reviewMapper.selectAll();
    }

    @Override
    public Review findById(Integer id) {
        return reviewMapper.selectById(id);
    }

    @Override
    public int save(Review review) {
        return reviewMapper.insert(review);
    }

    @Override
    public void update(Review review) {
        reviewMapper.update(review);
    }

    @Override
    public void deleteById(Integer id) {
        reviewMapper.deleteById(id);
    }

    @Override
    public void deleteForBook(String isbn) {
        reviewMapper.deleteForBook(isbn);
    }


}
