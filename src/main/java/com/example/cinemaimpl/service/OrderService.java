package com.example.cinemaimpl.service;

import com.example.cinemaimpl.dto.CustomPage;
import com.example.cinemaimpl.dto.OrderWithMovieDto;
import com.example.cinemaimpl.repository.OrderSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public interface OrderService {

    OrderWithMovieDto getById(Long id);
    OrderWithMovieDto create(OrderWithMovieDto OrderDto);
    OrderWithMovieDto update(OrderWithMovieDto OrderDto);
    void delete(Long id);
    Page<OrderWithMovieDto> getByAnyParam(OrderSearchCriteria orderSearchCriteria, CustomPage customPage);
}
