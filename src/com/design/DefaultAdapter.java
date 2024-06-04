package com.design;

import java.util.EventListener;

public class DefaultAdapter {

    /**
     * adapter 字眼的模式，代表著會有一方制定了規範，而另一方必須滿足規範，要滿足規範的那方就是 adapter。
     * Default Adapter 就是提供預設實作的 adapter，主要概念是，如果你的函式需要一組行為，
     * 而客戶端可能只對其中幾個行為感興趣，可以這組行為提供預設動作（不一定是空動作），不強迫客戶端處理他們不感興趣的東西。
     */
    public static void main(String[] args) {

        ContextListener listener = new TestBefore8Listener();
        listener.init("main init");
    }
}

//before java8
interface ContextListener extends EventListener {

    public void init(String init);
    public void destroy(String destroy);
}

abstract class TestAdapter implements ContextListener {
    public void init(String init) {
        //do nothing
    }
    public void destroy(String destroy) {
        //do nothing
    }
}
//TestBefore8Listener就像個配接器，讓你只處理感興趣的，這算是實現了關注分離吧
class TestBefore8Listener extends TestAdapter {
    public void init(String init) {
        System.out.println("before java8 init start: " + init);
    }
}

//after java8
interface ContextAfter8Listener extends EventListener {
    default void init(String init) {
        //do nothing
    }
    default void destroy(String destroy) {
        //do nothing
    }
}

//因為有default，所以可以直接繼承，不需要中間再藉由abstract class來實現關注分離
class TestAfter8Listener implements ContextAfter8Listener {

    @Override
    public void init(String init) {
        System.out.println("after java8 init start: " + init);
    }
}

