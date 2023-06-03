package com.spring.demo.condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author ZhouGang
 * @data: 2023/1/3 17:11
 **/
public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        //AbstractQueuedSynchronizer
        BoundedBuffer buffer = new BoundedBuffer();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() -> {
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "=" + buffer.take());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(Thread.currentThread().getName());
        int i = 0;
        while (true) {
            Thread.sleep(1000);
            buffer.put(++i);
        }
    }
}
