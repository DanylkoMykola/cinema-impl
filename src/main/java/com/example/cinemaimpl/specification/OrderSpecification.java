package com.example.cinemaimpl.specification;

import com.example.cinemaimpl.entity.Order;
import com.example.cinemaimpl.entity.Order_;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.example.cinemaimpl.utils.DateTimeUtils.*;


@Component
@AllArgsConstructor
public class OrderSpecification {

    public Specification<Order> hasId(Long id) {
        return (root, cq, cb) -> Objects.isNull(id) ? null : cb.equal(root.get(Order_.ID), id);
    }
    public Specification<Order> hasName(String customerName) {
        return (root, cq, cb) -> Objects.isNull(customerName) ? null : cb.like(root.get(Order_.CUSTOMER_NAME), "%" + customerName + "%");
    }
    public Specification<Order> hasPrice(BigDecimal price) {
        return (root, cq, cb)  -> Objects.isNull(price) ? null : cb.equal(root.get(Order_.PRICE), price);
    }
    public Specification<Order> hasOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, cq, cb) -> cb.between(root.get(Order_.ORDERED_DATE),
                Objects.isNull(startDate) ? MIN : startDate,
                Objects.isNull(endDate) ? MAX : endDate);
    }
}
