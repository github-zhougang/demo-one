package com.spring.demo.service.impl;

import com.spring.demo.dao.OrderDao;
import com.spring.demo.entity.Order;
import com.spring.demo.mapper.OrderMapper;
import com.spring.demo.service.AbsOrderService;
import com.spring.demo.service.OrderService;
import com.spring.demo.vo.SourceEnums;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhouGang
 * @data: 2022/12/31 00:16
 **/
@Slf4j
@Service
public class OrderServiceImpl extends AbsOrderService {

    @Autowired
    private OrderDao orderDao;

    public OrderServiceImpl(ProductService2Impl service2) {
        super(service2);
    }

    @Override
    public SourceEnums sourceEnums() {
        return SourceEnums.WE_CHAT;
    }

    @Override
    public Order getById(Long id) {
        if (id == null) {
            throw new RuntimeException("id is null!");
        }
        boolean b = StringUtils.equals("a", "a");
        if (b) {
            return orderDao.getById(id);
        }
        return null;
    }

}
