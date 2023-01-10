package com.warmup.mapper;

import com.warmup.domain.Review;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper {


    @Select("SELECT * FROM reviews")
    List<Review> selectAll();

    @Insert("""
            INSERT INTO reviews ( isbn, rating ) values ( #{isbn}, #{rating} )
            """)
    int insert(Review review);

    @Select("SELECT * FROM reviews R WHERE R.id = #{id}")
    Review selectById(Integer reviewId);
}
