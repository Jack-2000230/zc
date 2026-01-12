package com.example;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 功能描述：
 *
 * @author EDY
 * @date 2026/01/08
 */

@Service
public class OrderServiceJdk {

    @Resource(name = "jdkExecutor") // 注入刚才配置的原生对象
    private ThreadPoolExecutor threadPool;

    public void saveOrder(String orderId) {
        // 使用方式：显式提交任务
        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " [JDK] 处理订单: " + orderId);
            // 业务逻辑...
        });
    }
}