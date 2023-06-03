package com.spring.demo.service.impl;

import com.spring.demo.service.MultiThreadService;
import com.spring.demo.util.SynchroniseUtil;
import com.spring.demo.util.ThreadPoolExecutors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @author ZhouGang
 * @data: 2022/12/31 00:16
 **/
@Service
@Slf4j
public class MultiThreadServiceImpl implements MultiThreadService {

    @Override
    public <T, R> List<R> batchProcessCDL(List<T> dataList, Function<List<T>, R> function) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ThreadPoolExecutor executor = ThreadPoolExecutors.getSingletonExecutor();
        int unitLength = dataList.size() / ThreadPoolExecutors.getQueueSize() + 1;
        int synchroniseCount = dataList.size() % unitLength == 0 ?
                dataList.size() / unitLength : dataList.size() / unitLength + 1;
        log.info("任务个数：{}", synchroniseCount);
        SynchroniseUtil<R> synchroniseUtil = new SynchroniseUtil<>(synchroniseCount);
        for (int i = 0; i < dataList.size(); i += unitLength) {
            int toIndex = Math.min(i + unitLength, dataList.size());
            List<T> dataSubList = dataList.subList(i, toIndex);
            executor.execute(() -> synchroniseUtil.addResult(function.apply(dataSubList)));
        }
        List<R> list = synchroniseUtil.get(10, TimeUnit.SECONDS);
        long endTime = System.currentTimeMillis();
        log.info("执行时间：{} ms", endTime - startTime);
        return list;
    }

    @Override
    public <T, R> List<Future<R>> batchProcessCall(List<T> dataList, Function<List<T>, R> function) {
        ThreadPoolExecutor executor = ThreadPoolExecutors.getSingletonExecutor();
        int unitLength = dataList.size() / ThreadPoolExecutors.getQueueSize() + 1;
        List<Future<R>> futureList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i += unitLength) {
            int toIndex = Math.min(i + unitLength, dataList.size());
            List<T> dataSubList = dataList.subList(i, toIndex);
            futureList.add(executor.submit(() -> function.apply(dataSubList)));
        }
        return futureList;
    }

}
