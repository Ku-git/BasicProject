package com.openhome.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicDockType {

    public static void main(String[] args) {

        Dog dog = new Dog();
        try {
            doQuack(dog);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 不過模彷鴨子類型並不是沒有代價，也就是要付出效能作為補償
     * （根據實際的需求，也許你可分析看看，是否有哪些Method等反射物件可以快取，不用每次都動態生成，藉此改進一些效能）。
     */
    public static void doQuack(Object duck) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = duck.getClass().getMethod("quack");
        method.invoke(duck);
    }

}

class Dog {

    public void quack() {
        System.out.println("狗狗呱呱叫");
    }
}