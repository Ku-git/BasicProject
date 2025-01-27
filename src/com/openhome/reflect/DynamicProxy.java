package com.openhome.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DynamicProxy {

    public static void main(String[] args) {
        var helloProxy = (Hello) DynamicProxy.bind(new HelloSpeaker());
        helloProxy.hello("dynamic test");
    }


    /**
     * Proxy.newProxyInstance: 動態建立代理物件
     *  arg1: 呼叫時須指定類別載入器
     *  arg2: 告知要代理的介面
     *  arg3: 介面定義的方法被呼叫時的實際處理者
     *  依據當下物件，hello speak物件取得當前class loader，
     *  並依據其介面hello，後續實際處理者的物件並將hello後續實例化的物件綁定在一起，
     *  也就是hello speaker這個當前實例。
     *  依據剛剛的設定產生Proxy物件，並依同hello有的方法做呼叫
     *  Proxy會依據自己的邏輯產生Log並同時使用hello speaker的方法
     */
    public static Object bind(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new LoggingHandler(target)
        );
    }

    private static class LoggingHandler implements InvocationHandler {
        private Object target;

        LoggingHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {

            Object result = null;
            try {
                log(String.format("%s() start...", method.getName()));
                result = method.invoke(target, params);
                log(String.format("%s() end...", method.getName()));
            } catch (IllegalAccessException | IllegalArgumentException |
                    InvocationTargetException e) {
                log(e.getMessage());
            }
            return result;
        }

        private void log(String message) {
            Logger.getLogger(LoggingHandler.class.getName())
                    .log(Level.INFO, message);
        }
    }
}
