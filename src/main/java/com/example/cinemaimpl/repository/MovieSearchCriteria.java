package com.example.cinemaimpl.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class MovieSearchCriteria {
    private Long id;
    private String name;
    private LocalDate releaseDate;
}
