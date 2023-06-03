package com.spring.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.demo.entity.User;
import com.spring.demo.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhouGang
 */
@Repository
public class UserDao extends ServiceImpl<UserMapper, User> {

    @Resource
    private UserMapper userMapper;

    public List<User> selectAll() {
        return userMapper.selectList(new LambdaQueryWrapper<>());
    }

    public int insertBatch(List<User> list) {
        return userMapper.insertBatch(list);
    }

    public int insertBatchSomeColumn(List<User> list) {
        return userMapper.insertBatchSomeColumn(list);
    }
}

