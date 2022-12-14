package com.example.cinemaimpl.service;

import com.example.cinemaimpl.dto.CustomPage;
import com.example.cinemaimpl.dto.MovieWithOrderDto;
import com.example.cinemaimpl.dto.MovieDto;
import com.example.cinemaimpl.repository.MovieSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface MovieService {

    MovieWithOrderDto getById(Long id);
    MovieWithOrderDto create(MovieDto movieDto);
    MovieWithOrderDto update(MovieDto movieDto);
    void delete(Long id);
    Page<MovieWithOrderDto> getByAnyParam(MovieSearchCriteria criteria, CustomPage page);
}
