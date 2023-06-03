package com.spring.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhouGang
 * @date 2020/6/24
 */
@Configuration
@MapperScan({ "com.spring.demo.mapper" })
public class MybatisConfig {


}
