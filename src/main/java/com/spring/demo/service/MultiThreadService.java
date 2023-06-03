package com.spring.demo.service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Function;

/**
 * @author ZhouGang
 * @data: 2022/12/31 01:59
 **/
public interface MultiThreadService {

    <T, R> List<R> batchProcessCDL(List<T> dataList, Function<List<T>, R> function) throws InterruptedException;

    <T, R> List<Future<R>> batchProcessCall(List<T> dataList, Function<List<T>, R> function);
}
