package com.cyzs.thread;

/**
 * @author xh
 * @description:
 * @create 2020-04-04 20:26
 */
public class HelloThreadComm {

    public static void main(String[] args)throws Exception {
        Object o = new Object();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    synchronized (o){
                        System.out.println("告知老板数量");
                        try {
                            o.wait();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        System.out.println("好的，开吃了");
                        System.out.println("-----------------------");
                   }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    /*线程睡眠不能放到同步代码块里面，放进去之后通知不到其他线程
                    为什么是这样？，线程睡眠是不释放锁的，为什么醒来之后再notify唤不醒其他线程呢？
                    不是唤不醒其他线程，而是放在同步代码块里面，程序执行的太快，这个线程有重新获得了锁
                    */
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    synchronized (o){

                        System.out.println("包子做好了，给你");
                        try {
                            o.notify();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
}
