package com.openhome.database;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class MessageDAODemo3 {

    public static void main(String[] args) throws IOException {
        MessageDAO3 dao = new MessageDAO3(new SimpleConnectionPoolDataSource());
        var console = new Scanner(System.in);
        while (true) {
            System.out.println("(1) 顯示留言 (2) 新增留言 (3) 依條件顯示留言: ");
            switch (Integer.parseInt(console.nextLine())) {
                case 1:
                    doConcurrencyFindAll(dao);
                    break;
                case 2:
                    System.out.println("姓名:");
                    var name = console.nextLine();
                    System.out.println("email:");
                    var email = console.nextLine();
                    System.out.println("msg:");
                    var msg = console.nextLine();
                    dao.add(new Message(name, email, msg));
                    break;
                case 3://輸入: [test' --] -> sql injection
                    System.out.println("姓名:");
                    name = console.nextLine();
                    System.out.println("email:");
                    email = console.nextLine();
                    dao.findByCondition(name, email).forEach(message -> {
                        System.out.printf("%s %s %s%n",
                                message.name(),
                                message.email(),
                                message.msg());
                    });
            }
        }
    }

    /**
     * 使用多執行續併行執行處理db，並使用debug模式觀察連線池的運用
     */
    private static void doConcurrencyFindAll(MessageDAO3 messageDAO) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Consumer<MessageDAO3> selectAll = dao -> {
            dao.findAll().forEach(message -> {
                System.out.printf("%s %s %s%n",
                        message.name(),
                        message.email(),
                        message.msg());
            });
        };

        executorService.submit(() -> {
            selectAll.accept(messageDAO);
        });
        executorService.submit(() -> {
            selectAll.accept(messageDAO);
        });
        executorService.submit(() -> {
            selectAll.accept(messageDAO);
        });
        executorService.submit(() -> {
            selectAll.accept(messageDAO);
        });
        executorService.submit(() -> {
            selectAll.accept(messageDAO);
        });
        executorService.shutdown();
    }
}
