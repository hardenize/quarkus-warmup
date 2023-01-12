package com.warmup.mapper;

import com.warmup.domain.Review;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Select("SELECT EXISTS (SELECT 1 FROM reviews R WHERE R.review_id = #{id})")
    boolean existsById(Integer id);

    @Select("SELECT * FROM reviews")
    @Results(id = "reviewMap", value = {
            @Result(property = "reviewId", column = "review_id"),
            @Result(property = "isbn", column = "isbn"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "rating", column = "rating")
    })
    List<Review> selectAll();

    @Select("SELECT * FROM reviews R WHERE R.review_id = #{reviewId}")
    @ResultMap("reviewMap")
    Review selectById(@Param("reviewId") Integer reviewId);

    @Select("""
            INSERT INTO reviews ( isbn, rating, comment ) values ( #{isbn}, #{rating}, #{comment} )
            RETURNING review_id
            """)
    int insert(Review review);

    default void insertReviews(Iterable<Review> reviews) {
        if (reviews == null) {
            return;
        }
        for (Review review : reviews) {
            insert(review);
        }
    }

    @Update("UPDATE reviews SET comment = #{comment}, rating = #{rating}")
    void update(Review review);

    @Delete("DELETE FROM reviews WHERE isbn = #{isbn}")
    void deleteForBook(@Param("isbn") String isbn);

    @Delete("DELETE FROM reviews WHERE review_id = #{id}")
    void deleteById(@Param("id") Integer id);
}
