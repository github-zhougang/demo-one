package com.spring.demo.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.demo.entity.Order;
import com.spring.demo.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author ZhouGang
 */
@Repository
public class OrderDao extends ServiceImpl<OrderMapper, Order> {

    @Resource
    private OrderMapper orderMapper;


}

