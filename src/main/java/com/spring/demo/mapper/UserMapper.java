package com.spring.demo.mapper;

import com.spring.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZhouGang
 */
public interface UserMapper extends EasyBaseMapper<User> {

    int insertBatch(@Param("entities") List<User> entities);
}

