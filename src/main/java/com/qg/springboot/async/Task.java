package com.qg.springboot.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

/**
 * @author:Wilder Gao
 * @time:2018/4/5
 * @Discription：使用@Component 说明这个类已经被 spring 管理了
 */
@Component
public class Task {
    public static Random random = new Random();

    /**
     * 任务一，异步调用要使用Async注解
     */
    @Async
    public void doTaskOne() throws InterruptedException {
        System.out.println("开始执行任务一："+new Date());
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务一完成，一共花费时间："+(end - start) + "毫秒");
    }

    @Async
    public void doTaskTwo() throws InterruptedException {
        System.out.println("开始执行任务二："+new Date());
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务二完成，一共花费时间："+(end - start) + "毫秒");
    }

    @Async
    public void doTaskThree() throws InterruptedException {
        System.out.println("开始执行任务三"+new Date());
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
    }
}
