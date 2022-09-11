package com.example.cinemaimpl.service.impl;

import com.example.cinemaimpl.dto.MovieDto;
import com.example.cinemaimpl.dto.MovieWithOrderDto;
import com.example.cinemaimpl.entity.Movie;
import com.example.cinemaimpl.entity.Movie_;
import com.example.cinemaimpl.exception.NotFoundException;
import com.example.cinemaimpl.repository.MovieRepository;
import com.example.cinemaimpl.service.MovieService;
import com.example.cinemaimpl.specification.MovieSpecification;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.cinemaimpl.exception.constants.ErrorMessage.NOT_FOUND;

@Service
@AllArgsConstructor
@Transactional
public class MovieServiceImpl implements MovieService {

    private final MovieSpecification movieSpec;
    private final MovieRepository movieRepo;
    private final ModelMapper mapper;

    public MovieWithOrderDto getById(Long id) {
        Movie movie = movieRepo.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND + id));
        return mapper.map(movie, MovieWithOrderDto.class);
    }

    public MovieWithOrderDto create(MovieDto movieDto) {
        Movie movie = movieRepo.save(mapper.map(movieDto, Movie.class));
        return mapper.map(movie, MovieWithOrderDto.class);
    }

    public MovieWithOrderDto update(MovieDto movieDto) {
        Movie movie = movieRepo.findById(movieDto.getId()).orElseThrow(
                () -> new NotFoundException(NOT_FOUND + movieDto.getId()));
        mapper.map(movieDto, movie);
        return mapper.map(movieRepo.save(movie), MovieWithOrderDto.class);
    }

    public void delete(Long id) {
        if (movieRepo.existsById(id)) {
            movieRepo.deleteById(id);
        }  else {
            throw new NotFoundException(NOT_FOUND + id);
        }
    }

   public Page<MovieWithOrderDto> getByAnyParam(Long id, String name, LocalDate releaseDate) {
       Page<Movie> moviePages = movieRepo.findAll(Specification
                       .where(movieSpec.hasId(id)
                               .or(movieSpec.hasName(name))
                               .or(movieSpec.hasReleaseDate(releaseDate))),
               PageRequest.of(0,5, Sort.by(Movie_.NAME)));
       List<MovieWithOrderDto> movieWithOrderDtoList = moviePages.stream().map(movie -> mapper.map(movie, MovieWithOrderDto.class))
               .collect(Collectors.toList());
       return new PageImpl<>(movieWithOrderDtoList, moviePages.getPageable(), moviePages.getTotalElements());

   }
}