package com.spring.demo.condition;

/**
 * 中断线程
 *
 * @author ZhouGang
 * @data: 2023/1/3 17:11
 **/
public class Test2 {

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new MyTask());
        myThread.start();
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + "中断MyThread线程...");
        myThread.interrupt();
        System.out.println(myThread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            while (!thread.isInterrupted()) {
                try {
                    // do some task
                    // blocked by calling wait/sleep/join
                    System.out.print(thread.getName() + "=执行任务中...");
                    System.out.println("当前中断状态=" + thread.isInterrupted());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 如果该线程被中断，则会抛出InterruptedException异常
                    // 我们通过捕获这个异常，使得线程从block状态退出
                    thread.interrupt();// 这里使用interrupt(), 因为抛出InterruptedException有一个副作用: 清除当前的中断标志位false,
                    // 则显示的再调用一次interrupt(), 设置中断标志位为true
                    //break; // 这里使用break, 可以使我们在线程中断后退出死循环，从而终止线程。
                }
            }
        }
    }
}
