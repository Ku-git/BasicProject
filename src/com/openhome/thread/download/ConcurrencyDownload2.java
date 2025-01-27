package com.openhome.thread.download;

import java.util.concurrent.Executor;

public class ConcurrencyDownload2 {

    public static void main(String[] args) {

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

        new ConcurrencyDownload.Pages(urls, filesNames, new ConcurrencyDownload2.ThreadPerTaskExecutor()).download();
    }

    static class ThreadPerTaskExecutor implements Executor {

        @Override
        public void execute(Runnable runnable) {
            new Thread(runnable).start();
        }
    }
}
