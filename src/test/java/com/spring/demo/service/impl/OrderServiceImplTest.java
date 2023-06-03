package com.spring.demo.service.impl;

import com.spring.demo.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zhou Gang
 * @date 2023/3/5 03:35
 */
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Mock
    OrderMapper orderMapper;

    @Test
    void getById() {
        
    }
}