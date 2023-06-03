package com.spring.demo.vo;

import com.spring.demo.entity.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderVO extends Order {
    private String userName;
}