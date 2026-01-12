package com.example;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *
 * @author EDY
 * @date 2026/01/08
 */

@Service
public class OrderServiceSpring {

    // 不需要注入 Executor 对象，直接注解搞定
    @Async("springExecutor")
    public void saveOrder(String orderId) {
        System.out.println(Thread.currentThread().getName() + " [Spring] 处理订单: " + orderId);
        // 业务逻辑...
    }
}