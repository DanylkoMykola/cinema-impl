package com.example.cinemaimpl.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MovieDto {
    private Long id;
    @Pattern(regexp = "[ІіЇїҐґА-Яа-яa-zA-Z0-9\\s$&+,:;=?@#|'<>.^*()%!-]{2,255}", message = "Invalid name")
    private String name;
    private LocalDate releaseDate;
}
