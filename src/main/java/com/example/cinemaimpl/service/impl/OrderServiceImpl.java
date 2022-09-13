package com.example.cinemaimpl.service.impl;

import com.example.cinemaimpl.dto.CustomPage;
import com.example.cinemaimpl.dto.OrderWithMovieDto;
import com.example.cinemaimpl.entity.Order;
import com.example.cinemaimpl.entity.Order_;
import com.example.cinemaimpl.exception.NotFoundException;
import com.example.cinemaimpl.repository.OrderRepository;
import com.example.cinemaimpl.repository.OrderSearchCriteria;
import com.example.cinemaimpl.service.OrderService;
import com.example.cinemaimpl.specification.OrderSpecification;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.cinemaimpl.exception.constants.ErrorMessage.*;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderSpecification orderSpec;
    private final OrderRepository orderRepo;
    private final ModelMapper mapper;

    public OrderWithMovieDto getById(Long id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND + id));
        return mapper.map(order, OrderWithMovieDto.class);
    }

    public OrderWithMovieDto create(OrderWithMovieDto orderDto) {
        Order order = orderRepo.save(mapper.map(orderDto, Order.class));
        return mapper.map(order, OrderWithMovieDto.class);
    }

    public OrderWithMovieDto update(OrderWithMovieDto orderDto) {
        Order order = orderRepo.findById(orderDto.getId()).orElseThrow(
                () -> new NotFoundException(NOT_FOUND + orderDto.getId()));
        mapper.map(orderDto, order);
        return mapper.map(orderRepo.save(order), OrderWithMovieDto.class);
    }

    public void deleteByID(Long id) {
        orderRepo.deleteById(id);
    }

   public Page<OrderWithMovieDto> getByAnyParam(OrderSearchCriteria orderSearchCriteria, CustomPage customPage) {
       Specification<Order> specification = Specification.where(orderSpec.hasId(orderSearchCriteria.getId())
                       .and(orderSpec.hasName(orderSearchCriteria.getCustomerName()))
                       .and(orderSpec.hasPrice(orderSearchCriteria.getPrice()))
                       .and(orderSpec.hasOrderDateBetween(orderSearchCriteria.getStartDate(),
                               orderSearchCriteria.getEndDate())));
        List<OrderWithMovieDto> orders = orderRepo.findAll(specification).stream()
               .map(order -> mapper.map(order, OrderWithMovieDto.class))
               .collect(Collectors.toList());
       return new PageImpl<>(orders, PageRequest.of(customPage.getPageNumber(), customPage.getPageSize(),
               Sort.by(Objects.nonNull(customPage.getSortBy()) ? customPage.getSortBy() : Order_.CUSTOMER_NAME)), orders.size());

   }
}
