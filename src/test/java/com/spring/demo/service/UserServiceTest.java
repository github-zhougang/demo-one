package com.spring.demo.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.spring.demo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private List<User> userList = new ArrayList<>();

    //初始化时就创建好数据。模拟数据库已经存在的数据
    @BeforeEach
    public void createData() {
        long dataCount = 10000;

        // 创建用户数据。模拟已经插入到数据库的用户
        for (long i = 0; i < dataCount; i++) {
            User user = new User();
            //user.setId(i + 1);
            user.setId(IdWorker.getId());
            user.setUserName("用户名" + (i + 1));
            user.setEmail(getSaltString());
            user.setCreateTime(LocalDateTime.now().plusSeconds(i));
            userList.add(user);
        }
    }

    private static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    @Test
    public void listByIds() {
        List<Long> ids = userList.stream().map(User::getId).collect(Collectors.toList());
        for (User user : userService.listByIds(ids)) {
            System.out.println(user);
        }
    }

    @Test
    public void batchInsert() {
        System.out.println(userList.size());
        userService.saveBatch(userList);
    }

    @Test
    public static void main(String[] args) throws InterruptedException {
//        testCachedThreadPool();
//        custom();
//        synchronousQueue();
        Object[] items = new Object[100];
        System.out.println(items.length);
    }

    private static void threadPool() {
        // 线程池不同队列的区别, 学习队列
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> {});
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();

        AtomicInteger integer = new AtomicInteger();
        integer.incrementAndGet();
        new LinkedBlockingQueue<Integer>();
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
        try {
            queue.put(1);
            System.out.println(queue.poll(2, TimeUnit.SECONDS));
            boolean offer = queue.offer(2, 2, TimeUnit.SECONDS);
            System.out.println(offer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(queue);
    }

    private static void testCachedThreadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
    }

    private static void custom() {
        //实现自定义接口
        ExecutorService pool = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        System.out.println("线程" + r.hashCode() + "创建");
                        //线程命名
                        return new Thread(r, "threadPool" + r.hashCode());
                    }
                }, new ThreadPoolExecutor.CallerRunsPolicy()) {

            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行：" + ((ThreadTask) r).getTaskName());
            }

            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完毕：" + ((ThreadTask) r).getTaskName());
            }

            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i = 0; i < 10; i++) {
            pool.execute(new ThreadTask("Task" + i));
        }
        pool.shutdown();
    }

    static class ThreadTask implements Runnable {
        private String taskName;

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public ThreadTask(String name) {
            this.setTaskName(name);
        }

        public void run() {
            //输出执行线程的名称
            System.out.println("TaskName" + this.getTaskName() + "---ThreadName:" + Thread.currentThread().getName());
        }
    }

    private static void synchronousQueue() throws InterruptedException {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
        System.out.println(synchronousQueue.offer(1));
        System.out.println(synchronousQueue.size());
        Integer poll = synchronousQueue.poll();
        System.out.println(poll);
    }
}