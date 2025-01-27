package com.openhome.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class EfficiencyComparison {

    public static void main(String[] args) throws InterruptedException {
        // 初始化一個大列表
        List<Integer> numbersFor = new ArrayList<>();
        List<Integer> numbersStream = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            int num = random.nextInt(1000);
            numbersFor.add(num);
            numbersStream.add(num);
        }

        // 多次測試循環
        int iterations = 10;
        AtomicLong totalForTime = new AtomicLong();
        AtomicLong totalStreamTime = new AtomicLong();


            // 創建執行緒來測試for迴圈
            Thread forThread = new Thread(() -> {
                long startTimeFor = System.nanoTime();
                List<Integer> resultFor = new ArrayList<>();
                int current;
                for (Integer number : numbersFor) {
                    if (number % 2 == 0 || number % 3 == 0) {
                        current = number * 2 + 3;
                        if(current % 5 != 0) {
                            current = current / 2;
                            resultFor.add(current);
                        }
                    }

                }

                long endTimeFor = System.nanoTime();
                totalForTime.addAndGet((endTimeFor - startTimeFor));
            });

            // 創建執行緒來測試stream
            Thread streamThread = new Thread(() -> {
                long startTimeStream = System.nanoTime();
                List<Integer> resultStream = numbersStream.stream()
                        .filter(number -> number % 2 == 0)
                        .filter(number -> number % 3 == 0)
                        .map(number -> number * 2)
                        .map(number -> number + 3)
                        .filter(number -> number % 5 == 0)
                        .map(number -> number / 2)
                        .collect(Collectors.toList());
                long endTimeStream = System.nanoTime();
                totalStreamTime.addAndGet((endTimeStream - startTimeStream));
            });

            // 啟動執行緒
            forThread.start();
            streamThread.start();

            // 等待執行緒完成
            forThread.join();
            streamThread.join();


        // 計算平均時間
        long averageForTime = totalForTime.get() ;
        long averageStreamTime = totalStreamTime.get();

        System.out.println("Average For loop time: " + averageForTime + " ns");
        System.out.println("Average Stream time: " + averageStreamTime + " ns");
    }
}



