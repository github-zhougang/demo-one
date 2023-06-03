package com.spring.demo.service;

import com.spring.demo.entity.Order;
import com.spring.demo.vo.SourceEnums;

/**
 * @author ZhouGang
 * @data: 2022/12/30 23:57
 **/
public interface OrderService {

    SourceEnums sourceEnums();

    Order getById(Long id);

}
