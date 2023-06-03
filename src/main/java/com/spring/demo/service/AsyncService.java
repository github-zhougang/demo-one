package com.spring.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncService {
    private final static Logger logger = LoggerFactory.getLogger(AsyncService.class);

    /**
     * 表明该方法是异步方法。如果注解在类上，那表明类里面的所有方法都是异步
     * @param i
     */
    @Async
    public void executeAsyncTask(int i) {
        logger.info("\t 完成任务" + i);
        System.out.println("executeAsyncTask..." + Thread.currentThread().getName() + "：" + i);
    }

    @Async
    public Future<Long> subByAsync() throws Exception {
        long start = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();
        System.out.println("subByAsync..." + Thread.currentThread() + "耗时：" + (end - start));
        return new AsyncResult<>(end - start);
    }
}