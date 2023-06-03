package com.spring.demo.service;

import com.spring.demo.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private DataSource dataSource;

    private List<Product> productList = new ArrayList<>();

    //初始化时就创建好数据。模拟数据库已经存在的数据
    @BeforeEach
    public void createData() {
        long dataCount = 1000;

        // 创建用户数据。模拟已经插入到数据库的用户
        for (long i = 0; i < dataCount; i++) {
            Product product = new Product();
            product.setProductName("产品名" + (i + 1));
            product.setCreateTime(LocalDateTime.now().plusSeconds(i));
            productList.add(product);
        }
    }

    @Test
    public void batchInsert() {
        productService.batchInsert(productList);
    }
}