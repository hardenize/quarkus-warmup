package com.warmup.mapper;

import com.google.common.collect.Lists;
import com.warmup.domain.Author;
import com.warmup.domain.Book;
import com.warmup.domain.Editor;
import com.warmup.domain.Reviewer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {

    // ---------------------------------- INSERT STATEMENTS --------------------------------------------
    @Insert("""
            INSERT INTO books (
                               isbn,
                               title,
                               rating,
                               genre,
                               publisher,
                               published_on
                               )
            VALUES (
                    #{isbn},
                    #{title},
                    #{rating},
                    #{genre},
                    #{publisher},
                    #{publishedOn}
                    )
            """)
    void insert(Book book);

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

    @Select("""
            INSERT INTO people (
                                first_name,
                                last_name,
                                person_type
                                )
            VALUES (
                     #{firstName},
                     #{lastName},
                     'editor'
                    )
            RETURNING person_id
            """)
    int insertEditor(Editor editor);

    @Select("""
            INSERT INTO people (
                                first_name,
                                last_name,
                                person_type
                                )
            VALUES (
                     #{firstName},
                     #{lastName},
                     'reviewer'
                    )
            RETURNING person_id
            """)
    int insertReviewer(Reviewer reviewer);

    @Insert("""
            INSERT INTO people_books (
                                    person_id,
                                    book_id
                                )
            VALUES (
                     #{personId},
                     #{isbn}
                    )
            """)
    int insertPersonForBook(@Param("personId") Integer personId, @Param("isbn") String isbn);

    default void insertBooks(Iterable<Book> books) {
        for (Book book : books) {
            insert(book);
        }
    }

    default List<Integer> insertAuthors(Iterable<Author> authors) {
        List<Integer> ids = Lists.newArrayList();
        for (Author author : authors) {
             ids.add(insertAuthor(author));
        }
        return ids;
    }

    default List<Integer> insertEditors(Iterable<Editor> editors) {
        List<Integer> ids = Lists.newArrayList();
        for (Editor editor : editors) {
            ids.add(insertEditor(editor));
        }
        return ids;
    }

    default List<Integer> insertReviewers(Iterable<Reviewer> reviewers) {
        List<Integer> ids = Lists.newArrayList();
        for (Reviewer reviewer : reviewers) {
            ids.add(insertReviewer(reviewer));
        }
        return ids;
    }

    default int insertAuthorsForBook(Iterable<Integer> authorIds, String isbn) {
        int inserted = 0;
        for (Integer authorId : authorIds) {
            inserted += insertPersonForBook(authorId, isbn);
        }
        return inserted;
    }

    default int insertEditorsForBook(Iterable<Integer> editorIds, String isbn) {
        int inserted = 0;
        for (Integer editorId : editorIds) {
            inserted += insertPersonForBook(editorId, isbn);
        }
        return inserted;
    }

    default int insertReviewersForBook(Iterable<Integer> reviewerIds, String isbn) {
        int inserted = 0;
        for (Integer reviewerId : reviewerIds) {
            inserted += insertPersonForBook(reviewerId, isbn);
        }
        return inserted;
    }

    // ---------------------------------- SELECT STATEMENTS --------------------------------------------

    @Select("""
            SELECT * FROM books WHERE isbn = #{isbn}
            """)
    @Results(id = "bookMap", value = {
            @Result(property = "isbn", column = "isbn"),
            @Result(property = "title", column = "title"),
            @Result(property = "rating", column = "rating"),
            @Result(property = "genre", column = "genre"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "publishedOn", column = "published_on"),
            @Result(property = "editors",   column = "isbn", one = @One(select = "selectEditorsById")),
            @Result(property = "reviewers", column = "isbn", one = @One(select = "selectReviewersById")),
            @Result(property = "authors",   column = "isbn", one = @One(select = "selectAuthorsById"))
    })
    Book selectById(@Param("isbn") String isbn);

    @Select("SELECT * FROM books")
    @ResultMap("bookMap")
    List<Book> selectAll();

    @Select("""
            SELECT * FROM people p
                INNER JOIN people_books pb
                    ON p.person_id = pb.person_id
                WHERE pb.book_id = #{isbn} and p.person_type = 'author'
           """)
    @Results(id="personMap", value = {
            @Result(property = "personId", column = "person_id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    List<Author> selectAuthorsById(@Param("isbn") String isbn);

    @Select("""
            SELECT * FROM people p
                INNER JOIN people_books pb
                    ON p.person_id = pb.person_id
                WHERE pb.book_id = #{isbn} and p.person_type = 'reviewer'
           """)
    @ResultMap("personMap")
    List<Reviewer> selectReviewersById(@Param("isbn") String isbn);

    @Select("""
            SELECT * FROM people p
                INNER JOIN people_books pb
                    ON p.person_id = pb.person_id
                WHERE pb.book_id = #{isbn} and p.person_type = 'editor'
           """)
    @ResultMap("personMap")
    List<Editor> selectEditorsById(@Param("isbn") String isbn);
}