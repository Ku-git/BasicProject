package com.openhome.reflect;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StaticProxy {

    public static void main(String[] args) {
        HelloProxy proxy = new HelloProxy(new HelloSpeaker());
        proxy.hello("static proxy test");
    }

}

interface Hello {
    void hello(String name);
}

class HelloSpeaker implements Hello {

    @Override
    public void hello(String name) {
        System.out.println("hello " + name);
    }
}

class HelloProxy implements Hello {

    private Hello helloObj;

    public HelloProxy(Hello helloObj) {
        this.helloObj = helloObj;
    }

    @Override
    public void hello(String name) {
        log("hello() start...");
        helloObj.hello(name);
        log("hello() end...");
    }

    private void log(String message) {
        Logger.getLogger(HelloProxy.class.getName())
                .log(Level.INFO, message);
    }
}