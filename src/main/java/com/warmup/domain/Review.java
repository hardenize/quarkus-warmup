package com.warmup.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
public class Review {

    @Getter
    private final Integer reviewId;

    @Getter
    private final String isbn;

    @Getter
    private final String comment;

    @Getter
    private final int rating;
}
