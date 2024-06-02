package com.design;

public class BuilderOfEffectiveJava {

    /**
     * 建造者模式（Builder Pattern）的特色之一就是在對象構建完成後，不提供 setter方法來修改對象的屬性，從而保證對象的不可變性
     * 關注點是在選項的收集
     * 優點:
     * 可讀性和可維護性：代碼更具可讀性，且容易維護。
     * 靈活性：可以靈活地設置可選參數，且不需要多個構造函數。
     * 不可變性：建造出的對象是不可變的，對象在構造期間處於一致狀態。
     * 鏈式調用：鏈式調用使得代碼更加簡潔明了
     * 缺點:
     * 複雜性：相比於簡單的構造函數，Builder 模式的實現相對複雜。
     * 性能開銷：由於涉及多次方法調用和對象創建，會有一定的性能開銷
     *
     * ps.
     * 1. 不可變對象(immutable)
     * 不可變對象是指一旦創建，其狀態就不能被改變的對象。不可變對象有以下優點：
     * 簡單性：不需要考慮狀態變更的問題，代碼更簡單。
     * 安全性：在多線程環境下，不需要考慮同步問題。
     * 可共享性：可以自由地在不同上下文中共享，不用擔心狀態變更帶來的問題。
     *
     * 2. JavaBeans 風格的問題
     * 對象在構造期間可能處於不一致狀態：
     * 在 JavaBeans 風格中，對象的屬性是通過 setter 方法設置的。在調用所有 setter 方法之前，對象可能處於不一致狀態。
     * 無法保證不可變性：
     * 不可變對象是指一旦創建，其狀態就不能改變。這對於多線程環境下的並發安全非常有用。然而，JavaBeans 風格允許在對象構建之後修改其屬性，這樣就無法保證對象的不可變性。
     *
     */
    public static void main(String[] args) {

        var request = Request.newBuilder()
                .url("https://openhome.cc")
                .method("GET")
                .contentType("application/x-www-form-urlencoded")
                .queryString("keyword=java")
                .build();
        System.out.println(request.toString());
    }

}

class Request {
    private final String url;
    private final String method;
    private final String contentType;
    private final String queryString;
    private final String body;

    private Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.contentType = builder.contentType;
        this.queryString = builder.queryString;
        this.body = builder.body;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    static class Builder {
        private String url;
        private String method = "GET";
        private String contentType = "application/x-www-form-urlencoded";
        private String queryString = "";
        private String body = "";

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }
        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder queryString(String queryString) {
            this.queryString = queryString;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", contentType='" + contentType + '\'' +
                ", queryString='" + queryString + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
