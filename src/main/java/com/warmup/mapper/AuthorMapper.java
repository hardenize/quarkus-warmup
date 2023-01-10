package com.warmup.mapper;

import com.warmup.domain.Author;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthorMapper {

    @Select("SELECT * FROM people p WHERE p.person_type='author'")
    @Results(id="authorMap", value = {
            @Result(property = "personId", column = "person_id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    List<Author> selectAll();

    @Select("SELECT * FROM people p WHERE p.person_id = #{id}")
    @ResultMap("authorMap")
    Author selectById(@Param("id") Integer id);

    @Select(""" 
            SELECT * FROM people p
                WHERE p.first_name LIKE CONCAT('%', #{firstName}, '%') AND p.last_name LIKE CONCAT('%', #{lastName}, '%')
            """)
    @ResultMap("authorMap")
    List<Author> selectByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Select("""
            INSERT INTO people (
                                first_name,
                                last_name,
                                person_type
                                )
            VALUES (
                     #{firstName},
                     #{lastName},
                     'author'
                    )
            RETURNING person_id
            """)
    int insertAuthor(Author author);
}