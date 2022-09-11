package com.example.cinemaimpl.repository;

import com.example.cinemaimpl.CinemaImplApplication;
import com.example.cinemaimpl.entity.Order;
import com.example.cinemaimpl.entity.Order_;
import com.example.cinemaimpl.specification.OrderSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("H2")
@ContextConfiguration()
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CinemaImplApplication.class)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private  OrderSpecification orderSpec;

    @Test
    void findByAnyValue() {
        Page<Order> orderPages =  orderRepo.findAll(Specification
                        .where(orderSpec.hasId(1L)
                                .or(orderSpec.hasName("Tom"))
                                .or(orderSpec.hasPrice(null))
                                .or(orderSpec.hasReleaseDateBetween(null, null))),
                PageRequest.of(0,5, Sort.by(Order_.CUSTOMER_NAME)));

        List<Order> orders = orderPages.getContent();

        assertEquals(2L, orderPages.getTotalElements());
        assertEquals(1L, orders.get(0).getId());
        assertEquals(3L, orders.get(1).getId());
        assertEquals("Edward", orders.get(0).getCustomerName());
        assertEquals("Tom", orders.get(1).getCustomerName());
    }

}