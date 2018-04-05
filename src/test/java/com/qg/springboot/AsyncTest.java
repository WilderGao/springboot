package com.qg.springboot;

import com.qg.springboot.async.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author:Wilder Gao
 * @time:2018/4/5
 * @Discription：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTest {
    @Autowired
    private Task task;

    @Test
    public void task() throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println("开始执行所有任务......");
        task.doTaskThree();
        task.doTaskOne();
        task.doTaskTwo();
        Thread.sleep(2000);
        System.out.println("所有任务都执行完毕......");
        long end = System.currentTimeMillis();
        System.out.println("所用时间为:"+(end - start)+"毫秒");
    }
}
