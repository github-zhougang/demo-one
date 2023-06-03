package com.spring.demo.service;

import com.spring.demo.entity.User;

import java.util.List;

/**
 * @author ZhouGang
 * @data: 2022/12/30 23:57
 **/
public interface UserService {

    List<User> listByIds(List<Long> ids);

    boolean batchInsert(List<User> list);

    boolean saveBatch(List<User> list);

}
