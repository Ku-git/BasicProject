package com.openhome.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgTags {

    public static void main(String[] args) {

        Pattern regex = Pattern.compile("(?s)<img.+?src=\"(.+?)\".*?>");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://openhome.cc/index.html"))
                .build();
        HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(html -> {
                    Matcher matcher = regex.matcher(html);
                    while (matcher.find()) {
                        System.out.println(matcher.group());
                    }
                })
                .join();
    }
}
