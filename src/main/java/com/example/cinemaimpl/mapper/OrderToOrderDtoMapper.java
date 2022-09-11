package com.example.cinemaimpl.mapper;

import com.example.cinemaimpl.dto.MovieDto;
import com.example.cinemaimpl.dto.OrderWithMovieDto;
import com.example.cinemaimpl.entity.Order;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderDtoMapper extends AbstractConverter<Order, OrderWithMovieDto> {

    @Override
    protected OrderWithMovieDto convert(Order order) {
        return OrderWithMovieDto.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .price(order.getPrice())
                .orderedDate(order.getOrderedDate())
                .movie(MovieDto.builder()
                        .id(order.getMovie().getId())
                        .name(order.getMovie().getName())
                        .releaseDate(order.getMovie().getReleaseDate())
                        .build())
                .build();
    }
}
