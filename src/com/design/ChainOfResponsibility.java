package com.design;

import java.time.LocalDateTime;

public class ChainOfResponsibility {

    /**
     * 主要設計理念: 訊息傳播／各自職責
     * java.util.logging(Java 標準 API):
     * 包含了 Chain of Responsibility 的實現，以完成日誌訊息的傳播與處理，
     * 只不過它的 Logger 要更複雜一些，可允許使用者設定 Handler、Formatter
     * 與 Filter，實現各種日誌的輸入輸出、格式化，以及更複雜的訊息過濾。
     * servlet filter:
     * Java 的 Web 容器應用程式中的 Filter，它也是 Chain of Responsibility 的實現，
     * 而且 Filter 的 doFilter 傳入的 FilterChain，允許自訂 Filter 時，決定要不要將請求傳播下去
     * final point:
     * 這類設計可讓客戶端在使用 Logger、自訂日誌層級、Handler 等時，或者在 Web 容器中
     * 自定 Filter 這類物件時，可以有機會自掃門前雪；
     * 相對地，這也表示，自訂這類物件時，不要讓他們有什麼相依性，像是處理日誌時設定 Handler 時，
     * 別去管父 Logger 怎麼處理，或在 Web 容器中的 Filter，最好設計上各自獨立，別去考慮什麼前後順序關係之類的
     */
    public static void main(String[] args) {

        //base on warning
        Logger parent = new Logger("parent").setLevel(Level.WARNING);
        //base on severe
        Logger child = new Logger("child")
                .setLevel(Level.SEVERE)
                .setParent(parent);
        //傳送severe層級的訊息，child收到後this.severe = severe 層級，
        //後續傳播給parent，parent層級warning < severe
        child.log(Level.SEVERE, "嚴重訊息");
        //過不了child層級但因為會傳播給parent且層級通過
        child.log(Level.WARNING, "警告訊息");
        //過不了child & parent層級
        child.log(Level.INFO, "一般訊息");
    }

    /*
     * ex:
     *  public class PerformanceFilter extends HttpFilter {
     *     @Override
     *     protected void doFilter(
     *          HttpServletRequest request, HttpServletResponse response, FilterChain chain)
     *                 throws IOException, ServletException {
     *         long begin = System.currentTimeMillis();
     *
     *         chain.doFilter(request, response);
     *
     *         getServletContext().log("Request process in " +
     *                 (System.currentTimeMillis() - begin) + " milliseconds");
     *     }
     *  }
     *  這是因為 FilterChain 的 doFilter 實作概念類似以下：
     *  Filter filter = filterIterator.next();
     *  if(filter != null) {
     *     filter.doFilter(request, response, this);
     *  }
     *  else {
     *     targetServlet.service(request, response);
     *  }
     */
}

enum Level {
    INFO, WARNING, SEVERE;
}

class Logger {

    private String name;
    private Level level;
    private Logger parent;

    public Logger(String name) {
        this.name = name;
    }

    Logger setLevel(Level level) {
        this.level = level;
        return this;
    }

    public Logger setParent(Logger parent) {
        this.parent = parent;
        return this;
    }

    public Logger getParent() {
        return this.parent;
    }

    /**
     * 需求中，具有父子階層關係的 Logger，子 Logger 處理完日誌訊息後，日誌訊息要傳給父 Logger
     */
    void log(Level level, String message) {
        if (this.level.compareTo(level) <= 0) {
            System.out.printf("%s %s - %s: %s\n", name, level, LocalDateTime.now(), message);
        }

        if(getParent() != null) {
            getParent().log(level, message);
        }
    }
}