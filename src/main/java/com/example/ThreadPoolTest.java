package com.example;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：
 *
 * @author EDY
 * @date 2026/01/12
 */
@Slf4j
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolTaskExecutor executor = new SpringPoolConfig().springExecutor();

        executor.execute(() -> {
            log.info("核心线程处理[Spring] 处理订单: " + "1");
            ThreadUtil.sleep(1, TimeUnit.MINUTES);
        });

        executor.execute(() -> {
            log.info("先进入队列 非核心线程处理[Spring] 处理订单: " + "2");
            ThreadUtil.sleep(1, TimeUnit.MINUTES);
        });

        executor.execute(() -> {
            log.info("非核心线程处理[Spring] 处理订单: " + "3");
            ThreadUtil.sleep(1, TimeUnit.MINUTES);
        });

        executor.execute(() -> {
            log.info("主线程执行[Spring] 处理订单: " + "4");
            ThreadUtil.sleep(2, TimeUnit.MINUTES);
        });

        executor.execute(() -> {
            log.info("主线程执行[Spring] 处理订单: " + "5");
        });

    }

}
