package com.spring.demo.task;

import com.spring.demo.vo.OrderVO;
import com.spring.demo.entity.User;
import com.spring.demo.util.SynchroniseUtil;

import java.util.List;

public class OrderTask implements Runnable {
    private List<OrderVO> orderVOS;
    private List<User> users;
    private SynchroniseUtil<OrderVO> synchroniseUtil;

    public OrderTask(List<OrderVO> orderVOS,
                     List<User> users,
                     SynchroniseUtil<OrderVO> synchroniseUtil) {
        this.orderVOS = orderVOS;
        this.users = users;
        this.synchroniseUtil = synchroniseUtil;
    }

    @Override
    public void run() {
        //模拟从数据库里查数据
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (OrderVO orderVO : orderVOS) {
            for (User user : users) {
                if (orderVO.getUserId().equals(user.getId())) {
                    orderVO.setUserName(user.getUserName());
                    break;
                }
            }
        }

        synchroniseUtil.addResult(orderVOS);
    }
}