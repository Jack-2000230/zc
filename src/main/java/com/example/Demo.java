package com.example;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能描述：
 *
 * @author EDY
 * @date 2025/12/31
 */

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> integers = List.of(1, 2, 3, 1);
        Set<Integer> set = new HashSet<>(integers);
        List<Integer> list = set.stream().toList();

        Integer[] array = list.toArray(new Integer[0]);

        String[] strArray = new String[]{"A","B"};
        List<String> stringList = Arrays.asList(strArray);
        // UnsupportedOperationException
        // stringList.add("C");


        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }


    }
}
