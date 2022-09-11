package com.example.cinemaimpl.controller;

import com.example.cinemaimpl.service.OrderService;
import com.example.cinemaimpl.utils.MockData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController controller;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @SneakyThrows
    void getById() {
        mockMvc.perform(get("/order/1")).andExpect(status().isOk());

        verify( orderService).getById(1L);
    }

    @Test
    void getByAnyValue() {
        //TODO
    }

    @Test
    @SneakyThrows
    void create() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String requestBody = objectMapper.writeValueAsString(MockData.getOrderWithMovieDto());

        mockMvc.perform(post("/order")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(orderService).create(MockData.getOrderWithMovieDto());
    }

    @Test
    void update() {
        //TODO
    }

    @Test
    void delete() {
        //TODO
    }
}