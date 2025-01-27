package com.openhome;

import java.util.ArrayList;
import java.util.Arrays;

public class MultiChat {

    public static void main(String[] args) {
        Client c1 = new Client("Rex", "127.0.0.1");
        Client c2 = new Client("Edge", "192.168.0.1");

        ClientQueue queue = new ClientQueue();
        queue.addClientListener(new ClientListener() {

            @Override
            public void clientAdded(ClientEvent event) {
                System.out.printf("%s 從 %s 連線%n", event.getName(), event.getIp());
            }

            @Override
            public void clientRemoved(ClientEvent event) {
                System.out.printf("%s 從 %s 離線%n", event.getName(), event.getIp());
            }
        });

        queue.add(c1);
        queue.add(c2);

        queue.remove(c1);
        queue.remove(c2);
    }
}


class ClientQueue {

    private ArrayList<Client> clients = new ArrayList<>();
    private final ArrayList<ClientListener> listeners = new ArrayList<>();

    public void addClientListener(ClientListener clientListener) {
        listeners.add(clientListener);
    }

    public void add(Client client) {
        clients.add(client);
        ClientEvent event = new ClientEvent(client);
        for(ClientListener listener : listeners) {
            listener.clientAdded(event);
        }
    }

    public void remove(Client client) {
        clients.remove(client);
        ClientEvent event = new ClientEvent(client);
        for(ClientListener listener : listeners) {
            listener.clientRemoved(event);
        }
    }
}

interface ClientListener {
    void clientAdded(ClientEvent event);
    void clientRemoved(ClientEvent event);
}

class ClientEvent {
    private final Client client;

    public ClientEvent(Client client) {
        this.client = client;
    }

    public String getName() {
        return client.name;
    }

    public String getIp() {
        return client.ip;
    }
}

class Client {
    public final String name;
    public final String ip;

    public Client(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }
}