package com.spring.demo.service;

import com.spring.demo.entity.Product;

import java.util.List;

/**
 * @author ZhouGang
 * @data: 2022/12/30 23:57
 **/
public interface ProductService {

    Product getById(Long id);

    boolean batchInsert(List<Product> list);

}
