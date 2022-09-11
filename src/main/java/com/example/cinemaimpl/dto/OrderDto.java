package com.example.cinemaimpl.dto;



import lombok.*;
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
public class OrderDto {
    private Long id;
    @Pattern(regexp = "[ІіЇїҐґА-Яа-яa-zA-Z0-9\\s$&+,:;=?@#|'<>.^*()%!-]{2,255}", message = "Invalid name")
    private String customerName;
    @Min(1)
    private BigDecimal price;
    @Past
    private LocalDateTime orderedDate;
}
