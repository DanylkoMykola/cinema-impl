package com.example.cinemaimpl.controller;

import com.example.cinemaimpl.dto.OrderWithMovieDto;
import com.example.cinemaimpl.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<OrderWithMovieDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getById(id));
    }

    @GetMapping("/param")
    public ResponseEntity<Page<OrderWithMovieDto>> getByAnyValue(@RequestParam(required = false) Long id,
                                                                 @RequestParam(required = false) String customerName,
                                                                 @RequestParam(required = false) BigDecimal price,
                                                                 @RequestParam(required = false) LocalDateTime startDate,
                                                                 @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getByAnyParam(id, customerName, price, startDate, endDate));
    }

    @PostMapping()
    public ResponseEntity<OrderWithMovieDto> create(@RequestBody @Valid OrderWithMovieDto OrderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(OrderDto));
    }

    @PutMapping()
    public ResponseEntity<OrderWithMovieDto> update(@RequestBody @Valid OrderWithMovieDto OrderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(OrderDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
