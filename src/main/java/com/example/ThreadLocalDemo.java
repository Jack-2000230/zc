package com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;

/**
 * 功能描述：线程私有
 *
 * @author EDY
 * @date 2026/01/06
 */

public class ThreadLocalDemo {
    // 给每个线程设置一个默认值
    static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
    static ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();


    public static void main(String[] args) {
            Runnable runnable = () -> {
                // 在这里给每个线程设置默认值
                threadLocal2.set(2);
                int i = threadLocal2.get();
                i += 1;
                threadLocal2.set(i);
                System.out.println(Thread.currentThread().getName() + ": " + threadLocal2.get());
            };

        Callable<Void> callable = () -> {
            threadLocal2.set(1);
            int i = threadLocal2.get();
            i += 1;
            threadLocal2.set(i);
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal2.get());
            return null;
        };

        new Thread(runnable, "Thread-1").start();
        FutureTask<Void> voidFutureTask = new FutureTask<>(callable);
        CompletableFuture.runAsync(voidFutureTask);
        new Thread(voidFutureTask, "Thread-2").start();
    }

}
