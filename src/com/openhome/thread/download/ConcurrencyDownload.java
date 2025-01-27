package com.openhome.thread.download;

import java.io.FileOutputStream;
import java.util.concurrent.Executor;

public class ConcurrencyDownload {

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

        new Pages(urls, filesNames, new DirectExecutor()).download();
    }

    static class Pages {
        private String[] urls;
        private String[] fileNames;
        private Executor executor;

        public Pages(String[] urls, String[] fileNames, Executor executor) {
            this.urls = urls;
            this.fileNames = fileNames;
            this.executor = executor;
        }

        public void download() {
            for(int i = 0; i < urls.length; i++) {
                String url = urls[i];
                String fileName = fileNames[i];
                Runnable runnable = () -> {
                    try {
                        Download.dump(Download.openStream(url), new FileOutputStream(fileName));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                };
                executor.execute(runnable);
            }
        }
    }

    static class DirectExecutor implements Executor {

        @Override
        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

}
