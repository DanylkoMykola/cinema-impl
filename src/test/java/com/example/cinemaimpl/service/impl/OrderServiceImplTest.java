package com.example.cinemaimpl.service.impl;

import com.example.cinemaimpl.dto.OrderWithMovieDto;
import com.example.cinemaimpl.entity.Order;
import com.example.cinemaimpl.exception.NotFoundException;
import com.example.cinemaimpl.exception.constants.ErrorMessage;
import com.example.cinemaimpl.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static com.example.cinemaimpl.utils.MockData.getOrder;
import static com.example.cinemaimpl.utils.MockData.getOrderWithMovieDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepo;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private OrderServiceImpl orderService;


    @Test
    void getById() {
        when(orderRepo.findById(1L)).thenReturn(Optional.of(getOrder()));
        OrderWithMovieDto dto = orderService.getById(1L);

        assertEquals(getOrderWithMovieDto(), dto);
    }

    @Test
    void getByIdThrownNotFoundException() {
        when(orderRepo.findById(2L)).thenReturn(Optional.empty());

        Exception thrownException = assertThrows(NotFoundException.class, () -> orderService.getById(2L));
        assertEquals(ErrorMessage.NOT_FOUND + 2L, thrownException.getMessage());
    }

    @Test
    void create() {
        when(mapper.map(getOrderWithMovieDto(), Order.class)).thenReturn(getOrder());
        when(mapper.map(getOrder(), OrderWithMovieDto.class)).thenReturn(getOrderWithMovieDto());
        when(orderRepo.save(getOrder())).thenReturn(getOrder());
        OrderWithMovieDto dtoResult = orderService.create(getOrderWithMovieDto());

        assertEquals(1L, dtoResult.getId());
    }

    @Test
    void update() {
        //TODO
    }

    @Test
    void delete() {
        //TODO
    }

    @Test
    void getByAnyParam() {
        //TODO
    }
}