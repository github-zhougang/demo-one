package com.spring.demo.controller;

import com.spring.demo.entity.Product;
import com.spring.demo.response.R;
import com.spring.demo.service.impl.TestServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhougang
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestServiceImpl testService;

    @GetMapping("/lock1")
    public R<Product> testLock1() throws InterruptedException {
        testService.testLock1();
        return R.success();
    }

    @GetMapping("/lock2")
    public R<Product> testLock2() {
        testService.testLock2();
        return R.success();
    }

}