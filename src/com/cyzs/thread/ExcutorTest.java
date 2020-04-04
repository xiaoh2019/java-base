package com.cyzs.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author xh
 * @create 2020-02-13 19:39
 */
public class ExcutorTest {

    public static void main(String[] args) {
        Runnable r = new Runnable(){
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(50);
        //立即执行，每秒执行一次
        threadPoolExecutor.scheduleAtFixedRate(r, 0,1, TimeUnit.SECONDS);
    }

    @Test
    public void test2()throws Exception{
        Runnable r = new Runnable(){
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(50);
        //立即执行，每秒执行一次
        threadPoolExecutor.scheduleAtFixedRate(r, 0,1, TimeUnit.SECONDS);
        //防止线程终止
        Thread.currentThread().join();
    }

    @Test
    public void test1(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS
                , new LinkedBlockingQueue(100));

    }

    @Test
    public void test(){
        Thread thread = new Thread();
    }
}
