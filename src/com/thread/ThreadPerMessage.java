package com.thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ThreadPerMessage {

    /**
     * Java 的話，標準 API 就提供了 Executor 這類框架。
     * 一個請求一個執行緒，這就是 Thread-Per-Message
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        String[] uris = {
            "https://openhome.cc/zh-tw/algorithm/",
            "https://openhome.cc/zh-tw/computation/",
            "https://openhome.cc/zh-tw/toy-lang/",
            "https://openhome.cc/zh-tw/pattern/"
        };
        for (var uri: uris) {
            ThreadService.submit(() -> {
                try {
                    download(
                            uri,
                            uri.replace("https://openhome.cc/zh-tw/", "")
                                .replace("/", "")
                                .concat(".html"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        }
    }

    static InputStream openStream(String uri) throws IOException, InterruptedException {
        return HttpClient
                .newHttpClient()
                .send(
                    HttpRequest.newBuilder(URI.create(uri)).build(),
                    HttpResponse.BodyHandlers.ofInputStream()
                )
                .body();
    }

    static void download(String uri, String fileName) throws IOException, InterruptedException {
        Files.copy(openStream(uri), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
    }

}


class ThreadService {

    static void submit(Runnable runnable) {
        new Thread(runnable).start();
    }
}