package com.openhome.thread.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Download2 {

    public static void main(String[] args) throws Exception {
        String[] urls = {
                "http://openhome.cc/Gossip/Encoding/",
                "http://openhome.cc/Gossip/Scala/",
                "http://openhome.cc/Gossip/JavaScript/",
                "http://openhome.cc/Gossip/Python/",
        };

        String[] filesNames = {
                "Encoding.html",
                "Scala.html",
                "JavaScript.html",
                "Python.html",
        };

        //實際跟for loop執行一樣，所以沒有意義
        executeInOrder(urls, filesNames);
        //速度會較for loop or in order執行快很多
        concurrencyExecute(urls, filesNames);

    }

    private static void executeInOrder(String[] urls, String[] filesNames) throws InterruptedException {
        var start = System.currentTimeMillis();
        for (int i = 0; i < urls.length; i++) {
            int index = i;
            Thread thread = new Thread(() -> {
                try {
                    dump(openStream(urls[index]), new FileOutputStream(filesNames[index]));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            //每個執行緒啟動後立即 join，導致每次下載操作都會等待前一個操作完成
            thread.join();
        }
        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }

    private static void concurrencyExecute(String[] urls, String[] filesNames) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        var start = System.currentTimeMillis();
        for (int i = 0; i < urls.length; i++) {
            int index = i;
            Thread thread = new Thread(() -> {
                try {
                    dump(openStream(urls[index]), new FileOutputStream(filesNames[index]));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            threads.add(thread);
        }

        // 此方式是先啟動所有執行緒，然後再等待所有執行緒完成，達到並行下載的效果
        for(Thread thread : threads) {
            thread.join();
        }

        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }

    static InputStream openStream(String uri) throws Exception {
        return HttpClient.newHttpClient()
                .send(HttpRequest.newBuilder(URI.create(uri)).build(),
                        HttpResponse.BodyHandlers.ofInputStream())
                .body();
    }

    static void dump(InputStream src, OutputStream dest) throws IOException {

        try(src; dest) {
            byte[] data = new byte[1024];
            int length = 0;
            while ((length = src.read(data)) != -1) {
                dest.write(data, 0, length);
            }
        }
    }
}
