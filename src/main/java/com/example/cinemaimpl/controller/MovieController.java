package com.example.cinemaimpl.controller;

import com.example.cinemaimpl.dto.CustomPage;
import com.example.cinemaimpl.dto.MovieWithOrderDto;
import com.example.cinemaimpl.dto.MovieDto;
import com.example.cinemaimpl.repository.MovieSearchCriteria;
import com.example.cinemaimpl.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("{id}")
    public ResponseEntity<MovieWithOrderDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getById(id));
    }

    @GetMapping("/param")
    public ResponseEntity<Page<MovieWithOrderDto>> getByAnyValue(MovieSearchCriteria criteria, CustomPage page) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getByAnyParam(criteria, page));
    }

    @PostMapping()
    public ResponseEntity<MovieWithOrderDto> create(@RequestBody @Valid MovieDto movieDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.create(movieDto));
    }

    @PutMapping()
    public ResponseEntity<MovieWithOrderDto> update(@RequestBody @Valid MovieDto movieDto) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.update(movieDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
