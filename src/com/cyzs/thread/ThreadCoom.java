package com.cyzs.thread;


/**
 * @Description: 线程通信
 * @Author xh
 * @create 2020-04-04 12:33
 */
public class ThreadCoom {

    public static void main(String[] args)throws Exception {
        Object o = new Object();

        new Thread(){
            @Override
            public void run() {
                synchronized (o){
                    System.out.println("顾客：老板来十个包子");
                    try {
                        o.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    System.out.println("顾客：好的，开吃了");
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                synchronized (o){
                    try {
                        Thread.sleep(4000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("老板：包子做好了，给你");
                    try {
                        o.notify();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }
}
