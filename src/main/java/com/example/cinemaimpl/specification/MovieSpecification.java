package com.example.cinemaimpl.specification;

import com.example.cinemaimpl.entity.Movie;
import com.example.cinemaimpl.entity.Movie_;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class MovieSpecification {


    public Specification<Movie> hasId(Long id) {
        return (root, cq, cb) -> cb.equal(root.get(Movie_.ID), id);
    }
    public Specification<Movie> hasName(String name) {
        return (root, cq, cb) ->  cb.like(root.get(Movie_.name), "%" + name + "%");
    }
    public Specification<Movie> hasReleaseDate(LocalDate dateTime) {
        return (root, cq, cb) -> cb.equal(root.get(Movie_.releaseDate), dateTime);
    }
}