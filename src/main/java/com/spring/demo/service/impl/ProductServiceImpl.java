package com.spring.demo.service.impl;

import com.spring.demo.dao.ProductDao;
import com.spring.demo.entity.Product;
import com.spring.demo.service.MultiThreadService;
import com.spring.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhouGang
 * @data: 2022/12/31 00:16
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private MultiThreadService multiThreadService;

    public Product getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public boolean batchInsert(List<Product> list) {
        try {
            multiThreadService.batchProcessCDL(list, productDao::saveBatch);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
