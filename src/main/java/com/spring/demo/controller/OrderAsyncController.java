package com.spring.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
@RequestMapping("/order-async")
public class OrderAsyncController {

    private static final Queue<DeferredResult<String>> QUEUE = new ConcurrentLinkedQueue<>();

    @RequestMapping("/submit")
    public DeferredResult<String> submit() {
        DeferredResult<String> deferredResult = new DeferredResult<>(3000L, "create fail");
        QUEUE.add(deferredResult);
        return deferredResult;
    }

    @RequestMapping("/create")
    public String createOrder() {
        DeferredResult<String> poll = QUEUE.poll();
        String orderId = UUID.randomUUID().toString();
        poll.setResult(orderId);
        return "success->" + orderId;
    }
}