package com.spring.demo.service.impl;

import com.spring.demo.dao.UserDao;
import com.spring.demo.entity.User;
import com.spring.demo.service.MultiThreadService;
import com.spring.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhouGang
 * @data: 2022/12/31 00:16
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;
    @Autowired
    public MultiThreadService multiThreadService;

    @Override
    public List<User> listByIds(List<Long> ids) {
        List<Future<List<User>>> futures = multiThreadService.batchProcessCall(ids, userDao::listByIds);
        List<User> list = new ArrayList<>();
        try {
            for (Future<List<User>> future : futures) {
                list.addAll(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean batchInsert(List<User> list) {
        List<Future<Boolean>> futureList = multiThreadService.batchProcessCall(list, this::saveBatch);
        try {
            for (Future<?> future : futureList) {
                future.get(2, TimeUnit.SECONDS);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException("超时", e);
        }
        return true;
    }

    @Transactional
    public boolean saveBatch(List<User> list) {
        //return userDao.insertBatch(list) > 0;
        return userDao.saveBatch(list);
    }
}
