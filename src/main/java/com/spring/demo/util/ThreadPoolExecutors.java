package com.spring.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhouGang
 */
@Slf4j
public class ThreadPoolExecutors {
    private static final int PROCESSOR_NUMBER =
            Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = PROCESSOR_NUMBER;
    private static final int MAXIMUM_POOL_SIZE = PROCESSOR_NUMBER * 2 + 1;
    private static final int QUEUE_SIZE = 100;

    private static class ThreadPoolExecutorsHolder {
        private static final ThreadPoolExecutor INSTANCE =
                new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                        200, TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<>(QUEUE_SIZE));
    }

    private ThreadPoolExecutors() {
    }

    public static ThreadPoolExecutor getSingletonExecutor() {
        log.info("处理器数量：{}", PROCESSOR_NUMBER);
        return ThreadPoolExecutorsHolder.INSTANCE;
    }

    public static int getQueueSize() {
        return QUEUE_SIZE;
    }
}