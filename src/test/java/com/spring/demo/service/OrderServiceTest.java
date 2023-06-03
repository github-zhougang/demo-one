package com.spring.demo.service;

import com.spring.demo.util.SpringContextUtils;
import com.spring.demo.vo.OrderVO;
import com.spring.demo.vo.SourceEnums;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderServiceTest {

    private List<OrderVO> orderVOS = new ArrayList<>();

    //初始化时就创建好数据。模拟数据库已经存在的数据
    @PostConstruct
    public void createData() {
        long dataCount = 500;

        // 创建订单数据。模拟已经插入到数据库的订单
        for (long i = 0; i < dataCount; i++) {
            OrderVO orderVO = new OrderVO();
            orderVO.setId(i + 1);
            orderVO.setUserId(i + 1);
            //防止电脑太快，导致都是同一个时间，所以加一个数
            orderVO.setCreateTime(LocalDateTime.now().plusSeconds(i));
            orderVOS.add(orderVO);
        }
    }

    @Test
    public void batchInsert() {
        System.out.println();
    }
}