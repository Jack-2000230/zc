package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述：
 *
 * @author EDY
 * @date 2026/01/08
 */

@Configuration
public class JdkPoolConfig {

    //Bean 起个名
    @Bean("jdkExecutor")
    public ThreadPoolExecutor jdkExecutor() {
        // 1. 定义线程名工厂 (这是 JDK 最麻烦的地方)
        // 如果不用 Guava 或 Apache Commons 包，你得自己实现 ThreadFactory 接口写一堆代码
        // 这里为了演示原生痛点，我手写一个简单的 Factory
        ThreadFactory namedThreadFactory = new ThreadFactory() {
            private final AtomicInteger count = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "jdk-order-thread-" + count.getAndIncrement());
            }
        };

        // 2. 手动组装线程池
        // 参数顺序必须背下来：core, max, time, unit, queue, factory, handler
        return new ThreadPoolExecutor(
                // 核心线程数
                5,
                // 最大线程数
                10,
                // 最大线程数存活时间和单位单位
                60L, TimeUnit.SECONDS,
                // 队列 (必须指定容量)
                new ArrayBlockingQueue<>(100),
                // 工厂 (刚才手写的)
                namedThreadFactory,
                // 拒绝策略
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}