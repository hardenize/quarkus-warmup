package com.warmup.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Book {

    private String isbn;

    private String title;

    private List<Author> authors;

    private List<Editor> editors;

    private List<Reviewer> reviewers;

    private List<Review> reviews;

    private Integer rating;

    private String genre;

    private String publisher;

    private Date publishedOn;

}
