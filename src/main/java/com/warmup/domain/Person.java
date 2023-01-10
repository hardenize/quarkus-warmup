package com.warmup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Person {

    private Integer personId;

    private String firstName;

    private String lastName;

    @JsonIgnore
    private String personType;
}
