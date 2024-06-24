package com.design;

import java.util.ArrayList;
import java.util.List;

public class Mediator {

    /**
     * 原理及概念:
     *  Mediator（中介者）模式是一種行為型設計模式，旨在降低對象之間的耦合度。
     *  它通過一個中介者對象來封裝對象之間的交互，使對象之間不需要直接互相引用，從而使得它們可以鬆散耦合。
     *  這種模式的核心思想是，將對象之間的複雜通信轉移到中介者中，從而簡化對象之間的依賴關係。
     * 原因:
     *  原先設計是user和chat互相依賴，而此設計會隔離出來並達成降低耦合
     * 使用情境
     *  複雜對象通信：當系統中對象之間的通信非常複雜，需要簡化通信結構時。
     *  分離系統關注點：當需要分離系統的不同部分，使其不直接依賴時，可以使用中介者模式來進行調解和協調。
     * pros：
     *  1. 降低耦合度：中介者模式可以有效地降低對象之間的耦合度，使得系統中的對象可以更加獨立地工作，增加系統的靈活性和可維護性。
     *  2. 集中控制：中介者集中管理對象之間的交互和通信，使得對象之間的交互邏輯集中在一個地方，更加容易管理和維護。
     *  3. 便於擴展：中介者模式使得對象之間的依賴關係變得簡單，增加和移除對象不會影響其他對象的功能。
     * cons:
     *  1. 單一中介者的複雜性：隨著系統中對象數量的增加，中介者對象可能會變得複雜，成為一個“上帝對象”，
     *      負責過多的行為，影響系統的可維護性。
     *  2. 降低透明性：由於對象之間的交互是通過中介者進行的，可能會降低系統的透明性，使得理解和追蹤系統行為變得困難
     */
    public static void main(String[] args) {

        CharMediator chatRoom = new ChatRoom();

        User user1 = new ConcreteUser(chatRoom, "Alice");
        User user2 = new ConcreteUser(chatRoom, "Rex");
        User user3 = new ConcreteUser(chatRoom, "Ku");

        user1.send("hello!");
        user2.send("hello there");
    }
}

//Mediator（中介者）：定義一個接口，用於溝通同事（Colleague）對象。
interface CharMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

//ConcreteMediator（具體中介者）：實現中介者接口，協調各個同事對象之間的交互。
class ChatRoom implements CharMediator {

    List<User> users = new ArrayList<>();

    @Override
    public void sendMessage(String message, User user) {
        for(User client: users) {
            // 讓除了發送消息的用戶外的其他所有用戶接收消息
            if(client != user) {
                client.receive(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}

//Colleague（同事）：定義一個接口，持有中介者對象的引用，從而可以將請求轉發給中介者。
abstract class User {
    protected CharMediator mediator;
    protected String name;

    public User(CharMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);

    public abstract void receive(String message);
}

//ConcreteColleague（具體同事）：實現同事接口，通過中介者來進行通信。
class ConcreteUser extends User {

    public ConcreteUser(CharMediator mediator, String name) {
        super(mediator, name);
        mediator.addUser(this);// 自動將用戶添加到中介者中
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + " receives: " + message);
    }
}
