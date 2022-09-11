package com.example.cinemaimpl.dto;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class OrderWithMovieDto {
    private Long id;
    @Pattern(regexp = "[ІіЇїҐґА-Яа-яa-zA-Z0-9\\s$&+,:;=?@#|'<>.^*()%!-]{2,255}", message = "Invalid name")
    private String customerName;
    @Min(1)
    private BigDecimal price;
    @Past
    private LocalDateTime orderedDate;
    private MovieDto movie;
}
