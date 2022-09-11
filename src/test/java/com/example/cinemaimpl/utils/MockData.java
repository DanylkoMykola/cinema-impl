package com.example.cinemaimpl.utils;

import com.example.cinemaimpl.dto.MovieDto;
import com.example.cinemaimpl.dto.OrderWithMovieDto;
import com.example.cinemaimpl.entity.Movie;
import com.example.cinemaimpl.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MockData {

    public static Movie getMovie() {
        return Movie.builder()
                .id(1L)
                .name("Matrix")
                .releaseDate(LocalDate.of(2000,1,1))
                .build();
    }

    public static Order getOrder() {
        return Order.builder()
                .id(1L)
                .customerName("mr. Anderson")
                .price(BigDecimal.valueOf(14.15))
                .orderedDate(LocalDateTime.of(2001, 1, 1, 12, 10))
                .movie(getMovie())
                .build();
    }

    public static OrderWithMovieDto getOrderWithMovieDto() {
        return OrderWithMovieDto.builder()
                .id(1L)
                .customerName("mr. Anderson")
                .price(BigDecimal.valueOf(14.15))
                .orderedDate(LocalDateTime.of(2001, 1, 1, 12, 10))
                .movie(getMovieDto())
                .build();
    }

    public static MovieDto getMovieDto() {
        return MovieDto.builder()
                .id(1L)
                .name("Matrix")
                .releaseDate(LocalDate.of(2000,1,1))
                .build();
    }
}
