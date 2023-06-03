package com.spring.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SynchroniseUtil<T> {
    private CountDownLatch countDownLatch;

    private final List<T> result = Collections.synchronizedList(new ArrayList<>());

    public SynchroniseUtil(int count) {
        this.countDownLatch = new CountDownLatch(count);
    }

    public List<T> get() throws InterruptedException {
        countDownLatch.await();
        return this.result;
    }

    public List<T> get(long timeout, TimeUnit timeUnit) throws InterruptedException {
        if (countDownLatch.await(timeout, timeUnit)) {
            return this.result;
        } else {
            throw new RuntimeException("超时");
        }
    }

    public void addResult(T resultMember) {
        result.add(resultMember);
        countDownLatch.countDown();
    }

    public void addResult(List<T> resultMembers) {
        result.addAll(resultMembers);
        countDownLatch.countDown();
    }
}