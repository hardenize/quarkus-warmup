package com.warmup.mapper;

import com.warmup.domain.Author;
import com.warmup.domain.Editor;
import com.warmup.domain.Person;
import com.warmup.domain.Reviewer;
import org.apache.ibatis.annotations.Case;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.TypeDiscriminator;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AuthorMapper {

    @Select("SELECT EXISTS (SELECT 1 FROM people P WHERE P.person_id = #{id})")
    boolean existsById(Integer id);

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
         """)
    @TypeDiscriminator(column = "person_type",
            cases = {
                    @Case(value = "author", type = Author.class,
                            results = {
                                    @Result(property = "personId", column = "person_id"),
                                    @Result(property = "firstName", column = "first_name"),
                                    @Result(property = "lastName", column = "last_name"),
                                    @Result(property = "personType", column = "person_type")
                            }
                    ),
                    @Case(value = "editor", type = Editor.class,
                            results = {
                                    @Result(property = "personId", column = "person_id"),
                                    @Result(property = "firstName", column = "first_name"),
                                    @Result(property = "lastName", column = "last_name"),
                                    @Result(property = "personType", column = "person_type")
                            }
                    ),
                    @Case(value = "reviewer", type = Reviewer.class,
                            results = {
                                    @Result(property = "personId", column = "person_id"),
                                    @Result(property = "firstName", column = "first_name"),
                                    @Result(property = "lastName", column = "last_name"),
                                    @Result(property = "personType", column = "person_type")
                            }
                    )
            }
    )
    List<Person> selectAllByType();

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

    @Update("""
            UPDATE people
                SET first_name = #{firstName},
                    last_name  = #{lastName}
            WHERE person_id = #{personId}
            """)
    int updateAuthor(Author author);

    @Delete("""
            DELETE FROM people_books pb
            WHERE pb.person_id = #{id} AND pb.book_id = #{isbn}
          """)
    void deleteForBookByAuthorId(@Param("isbn") String isbn, @Param("id") Integer id);
}