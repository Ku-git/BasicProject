package com.openhome;

public class InitializationOrder {

    static class Inner {

        public Inner() {
            System.out.println("Test");
        }
    }

    // 靜態變數
    static int staticVar = initializeStaticVar();

    // 實例變數
    int instanceVar = initializeInstanceVar();

    // 靜態程式碼區塊
    static {
        System.out.println("靜態程式碼區塊 1");
    }

    // 實例初始化區塊
    {
        System.out.println("實例初始化區塊 1");
    }

    // 建構子
    public InitializationOrder() {
        System.out.println("建構子");
    }

    // 靜態程式碼區塊
    static {
        System.out.println("靜態程式碼區塊 2");
    }

    // 實例初始化區塊
    {
        System.out.println("實例初始化區塊 2");
    }

    // 靜態方法初始化靜態變數
    private static int initializeStaticVar() {
        System.out.println("靜態變數初始化");
        return 1;
    }

    // 實例方法初始化實例變數
    private int initializeInstanceVar() {
        System.out.println("實例變數初始化");
        return 1;
    }

    public static void main(String[] args) {
        System.out.println("Main 方法開始");
        InitializationOrder obj = new InitializationOrder();
        InitializationOrder.Inner inner = new InitializationOrder.Inner();
        System.out.println("Main 方法結束");
    }
}
