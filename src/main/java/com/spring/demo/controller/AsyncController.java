package com.spring.demo.controller;

import com.spring.demo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    AsyncService asyncTaskService;
    @GetMapping("/t1")
    public String t1(){
        long start = System.currentTimeMillis();
        System.out.println("主线程开始..." + Thread.currentThread());
        for (int i = 0; i < 10; i++) {
            asyncTaskService.executeAsyncTask(i);
        }
        System.out.println("主线程结束..." + Thread.currentThread() + "-->" + (System.currentTimeMillis() - start));
        return "t1";
    }

    @GetMapping("/t2")
    public String t2() throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("主线程开始..." + Thread.currentThread());
        Future<Long> task = asyncTaskService.subByAsync();
        System.out.println(task.get());
        System.out.println("主线程结束..." + Thread.currentThread() + "-->" + (System.currentTimeMillis() - start));
        return "t2";
    }

    @GetMapping("/t3")
    public Callable<String> t3() {
        long start = System.currentTimeMillis();
        System.out.println("主线程开始..." + Thread.currentThread());
        Callable<String> callable = () -> {
            System.out.println("callable线程..." + Thread.currentThread() + "-->" + System.currentTimeMillis());
            Thread.sleep(3000);
            return "callable async";
        };
        System.out.println("主线程结束..." + Thread.currentThread() + "-->" + (System.currentTimeMillis() - start));
        return callable;
    }
}