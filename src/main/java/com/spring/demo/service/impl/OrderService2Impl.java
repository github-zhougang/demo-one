package com.spring.demo.service.impl;

import com.spring.demo.dao.OrderDao;
import com.spring.demo.entity.Order;
import com.spring.demo.service.AbsOrderService;
import com.spring.demo.service.OrderService;
import com.spring.demo.vo.SourceEnums;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhouGang
 * @data: 2022/12/31 00:16
 **/
@Slf4j
@Service
public class OrderService2Impl extends AbsOrderService {

    @Autowired
    private OrderDao orderDao;

    public OrderService2Impl(ProductServiceImpl service) {
        super(service);
    }

    @Override
    public SourceEnums sourceEnums() {
        return SourceEnums.APP;
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
