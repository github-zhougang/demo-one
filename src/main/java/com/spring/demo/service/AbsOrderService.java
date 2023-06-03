package com.spring.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ZhouGang
 * @data: 2022/12/30 23:57
 **/
public abstract class AbsOrderService implements OrderService {

    @Autowired
    public AbsOrderService(ProductService service) {
        System.out.println(service);
    }

}
