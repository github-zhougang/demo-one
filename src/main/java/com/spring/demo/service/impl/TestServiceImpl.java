package com.spring.demo.service.impl;

import com.spring.demo.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author ZhouGang
 * @data: 2022/12/31 00:16
 **/
@Slf4j
@Service
public class TestServiceImpl {

    @Resource
    private TestMapper testMapper;

    @Transactional(rollbackFor = Exception.class)
    public void testLock1() throws InterruptedException {
        testMapper.insertOne(UUID.randomUUID().toString().replace("-", ""));
        try {
            testMapper.deleteOne(UUID.randomUUID().toString().substring(10, 18));
            System.out.println("one 获取 xdx_test_one 表锁");
            Thread.sleep(3000);
            testMapper.deleteTwo(UUID.randomUUID().toString().substring(10, 18));
            System.out.println("one 获取 xdx_test_two 表锁");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("one发生了异常：{}", e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void testLock2() {
        //testMapper.insertTwo(UUID.randomUUID().toString().replace("-", ""));
        testMapper.deleteTwo(UUID.randomUUID().toString().substring(10, 18));
        System.out.println("two 获取 xdx_test_two 表锁");
        testMapper.deleteOne(UUID.randomUUID().toString().substring(10, 18));
        System.out.println("two 获取 xdx_test_one 表锁");
    }
}
