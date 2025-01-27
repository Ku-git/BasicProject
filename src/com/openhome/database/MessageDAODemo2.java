package com.openhome.database;

import java.util.Scanner;

public class MessageDAODemo2 {

    public static void main(String[] args) {
        var url = "jdbc:h2:tcp://localhost/C:/Users/asus/TestProject/BasicProject";
        var user = "admin";
        var password = "admin";

        MessageDAO2 dao = new MessageDAO2(url, user, password);
        var console = new Scanner(System.in);
        while (true) {
            System.out.println("(1) 顯示留言 (2) 新增留言 (3) 依條件顯示留言: ");
            switch (Integer.parseInt(console.nextLine())) {
                case 1:
                    dao.findAll().forEach(message -> {
                        System.out.printf("%s %s %s%n",
                                message.name(),
                                message.email(),
                                message.msg());
                    });
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
}
