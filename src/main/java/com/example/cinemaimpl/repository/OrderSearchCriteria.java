package com.example.cinemaimpl.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderSearchCriteria {
    private Long id;
    private String customerName;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
