package com.design;

import java.util.LinkedList;
import java.util.List;

public class Observer {

    /**
     * Observer 模式，也有人稱為 Publish-subscribe 模式，事件處理基本是 Observer 模式的應用之一，
     * 只不過有時候感興趣的時機，不見得是某個物件的狀態變化，可能是某個模組甚至是應用程式生命週期的變化。
     * 如果應用程式本身負有某種生命週期管理的職責，在一開始規劃時，有經驗的開發者可能就會想在某些生命週期變化時，
     * 提供事件處理機制，因而一開始就定義各種傾聽器介面，實現 Observer 的概念。
     * 主要目的:
     *  觀察者模式的核心是主題（Subject:clientQueue）和觀察者（Observer: clientListener）之間的一對多依賴關係，
     *  當主題的狀態發生變化時，它會通知所有依賴它的觀察者。
     * 使用情境:
     *  1. 事件處理系統：需要在狀態變化時自動通知其他物件進行更新的場景，如 GUI 事件處理、訂閱-發布系統等。
     *  2. 數據推送：需要將數據更新即時推送給多個接收者的場景，如新聞訂閱、天氣預報等。
     *  3. 分佈式系統：在分佈式環境下，主題變化需要同步通知到多個節點。
     */
    public static void main(String[] args) {

        ClientQueue clientQueue = new ClientQueue();
        clientQueue.addClientListener(new ClientLogger());
        var c1 = new Client("127.0.0.1", "Ku");
        var c2 = new Client("192.168.0.2", "Edge");
        clientQueue.add(c1);
        clientQueue.add(c2);
        clientQueue.remove(c2);
        clientQueue.remove(c1);
    }
}

//可以針對不同的類型(實作)做通知
interface ClientListener {
    void clientAdd(EventObject event);
    void clientRemove(EventObject event);
}

class EventObject {
    private Client client;

    EventObject(Client client) {
        this.client = client;
    }

    public Object getSource() {
        return client;
    }
}

class ClientLogger implements ClientListener {

    @Override
    public void clientAdd(EventObject event) {
        Client client = (Client) event.getSource();
        System.out.println(client.ip + " added");
    }

    @Override
    public void clientRemove(EventObject event) {
        Client client = (Client) event.getSource();
        System.out.println(client.ip + " removed");
    }
}

class ClientQueue {
    private List<Client> clients = new LinkedList<>();
    private List<ClientListener> listeners = new LinkedList<>();

    void addClientListener(ClientListener listener) {
        listeners.add(listener);
    }

    void removeClientListener(ClientListener listener) {
        listeners.remove(listener);
    }

    void add(Client client) {
        clients.add(client);
        notifyAdded(client);
    }

    void remove(Client client) {
        clients.remove(client);
        notifyRemoved(client);
    }

    private void notifyAdded(Client client) {
        for(var listener: listeners) {
            listener.clientAdd(new EventObject(client));
        }
    }

    private void notifyRemoved(Client client) {
        for(var listener: listeners) {
            listener.clientRemove(new EventObject(client));
        }
    }
}

class Client {

    String name;
    String ip;

    public Client(String ip, String name) {
        this.ip = ip;
        this.name = name;
    }

}