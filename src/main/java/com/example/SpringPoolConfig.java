package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor; // 注意这里引用的是拒绝策略的常量

/**
 * 功能描述：
 *
 * @author EDY
 * @date 2026/01/08
 */
@Configuration
public class SpringPoolConfig {

    @Bean("springExecutor")
    public ThreadPoolTaskExecutor springExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // 1. 参数配置 (Setter 模式，更易读)
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(1);
        executor.setKeepAliveSeconds(60);

        // 2. 线程命名 (Spring 的超级优势，一行代码搞定)
        executor.setThreadNamePrefix("spring-order-thread-");

        // 3. 拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 4. 优雅停机 (JDK 原生没有的自带功能)
        // 容器关闭时，等待任务执行完再销毁 Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);

        executor.initialize();
        return executor;
    }
}