package com.openhome.http;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Search {

    public static void main(String[] args) throws IOException, InterruptedException {

        Map<String, String> map = Map.of("q", "Java SE 17 技術手冊", "lr", "lang_zh-TW");
        URI uri = URI.create("https://www.google.com/search?" +
                RequestHelper.queryString(map, "UTF-8"));
        System.out.println(uri);

        HttpRequest request = HttpRequest
                .newBuilder(uri)
                .header("User-Agent", "Mozilla/5.0")
                .GET().build();
        String responseBody = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString())
                .body();
        System.out.println(responseBody);
    }
}
