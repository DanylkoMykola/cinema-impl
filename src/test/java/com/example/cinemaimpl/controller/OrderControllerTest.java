package com.example.cinemaimpl.controller;

import com.example.cinemaimpl.service.OrderService;
import com.example.cinemaimpl.utils.MockData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
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
    void getById() throws Exception {
        mockMvc.perform(get("/order/1")).andExpect(status().isOk());

        verify( orderService).getById(1L);
    }

    @Test
    void getByAnyValue() throws Exception {
        mockMvc.perform(get("/order/param?id=3&customerName=Tom")).andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
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
    void update() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String requestBody = objectMapper.writeValueAsString(MockData.getOrderWithMovieDto());

        mockMvc.perform(put("/order")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orderService).update(MockData.getOrderWithMovieDto());
    }

    @Test
    void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/order/1" )).andExpect(status().isOk());

        verify( orderService).deleteByID(1L);
    }
}