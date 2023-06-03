package com.spring.demo.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.demo.entity.Product;
import com.spring.demo.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author ZhouGang
 */
@Repository
public class ProductDao extends ServiceImpl<ProductMapper, Product> {

    @Resource
    private ProductMapper productMapper;

}

