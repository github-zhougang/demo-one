package com.spring.demo.controller;

import com.spring.demo.entity.Product;
import com.spring.demo.response.R;
import com.spring.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhougang
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    @GetMapping("/{id}")
    public R<Product> get(@PathVariable String id) throws Exception {
        return R.success(productService.getById(Long.valueOf(id)));
    }

}