package com.example.cinemaimpl.mapper;

import com.example.cinemaimpl.dto.MovieWithOrderDto;
import com.example.cinemaimpl.dto.OrderDto;
import com.example.cinemaimpl.entity.Movie;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MovieToMovieDtoMapper extends AbstractConverter<Movie, MovieWithOrderDto> {

    @Override
    protected MovieWithOrderDto convert(Movie movie) {
        return MovieWithOrderDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .releaseDate(movie.getReleaseDate())
                .orders(Optional.ofNullable(movie.getOrders()).orElse(Collections.emptyList()).stream()
                        .map(orders -> OrderDto.builder()
                                .id(orders.getId())
                                .customerName(orders.getCustomerName())
                                .price(orders.getPrice())
                                .orderedDate(orders.getOrderedDate())
                                .build())
                        .collect(Collectors.toList())).build();
    }
}
