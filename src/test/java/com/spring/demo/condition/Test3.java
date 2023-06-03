package com.spring.demo.condition;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程顺序执行
 *
 * @author ZhouGang
 * @data: 2023/1/3 17:11
 **/
public class Test3 {

    public static void main(String[] args) {
//        useCondition();
//        useCountDownLatch();
        useSingleThreadExecutor();
    }

    private static void useCondition() {
        AtomicBoolean t1Run = new AtomicBoolean(false);
        AtomicBoolean t2Run = new AtomicBoolean(false);
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Thread t0 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
            lock.lock();
            try {
                t1Run.set(true);
                System.out.println(Thread.currentThread().getName() + "执行完成");
                condition1.signal();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "Thread-0");
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
            lock.lock();
            try {
                if (!t1Run.get()) {
                    condition1.await();
                }
                t2Run.set(true);
                System.out.println(Thread.currentThread().getName() + "执行完成");
                condition2.signal();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "Thread-1");
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
            lock.lock();
            try {
                if (!t2Run.get()) {
                    condition2.await();
                }
                System.out.println(Thread.currentThread().getName() + "执行完成");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "Thread-2");
        t2.start();
        t1.start();
        t0.start();
    }

    private static void useCountDownLatch() {
        CountDownLatch countDownLatch1 = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        Thread t0 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
            // 唤醒阻塞线程t1
            System.out.println(Thread.currentThread().getName() + "执行完成");
            countDownLatch1.countDown();
        }, "Thread-0");
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
            try {
                countDownLatch1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 唤醒阻塞线程t2
            System.out.println(Thread.currentThread().getName() + "执行完成");
            countDownLatch2.countDown();
        }, "Thread-1");
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
            try {
                // t2等待
                countDownLatch2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完成");
        }, "Thread-2");
        t2.start();
        t1.start();
        t0.start();
    }

    private static void useSingleThreadExecutor() {
        Thread t0 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "-0执行完成"));
        Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "-1执行完成"));
        Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "-2执行完成"));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(t0);
        executorService.execute(t1);
        executorService.execute(t2);
        executorService.shutdown();
    }
}
