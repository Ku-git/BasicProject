package com.openhome.reflect;

public class ClassForNameDemo {

    public static void main(String[] args) {

        //產生物件實例時，執行static block
        Some some = new Some();

        //預先載入Class, 並且會在載入過程直接執行static block
        Class clz1 = defaultInit();
        //Class loader
        ClassLoader classLoader = clz1.getClassLoader();
        System.out.println(classLoader);
        //class loader parent
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());

        //預先載入class時不會執行static block，同產生物件實例時才會執行static block
        Class clz2 = laterInit();
    }

    public static Class defaultInit() {

        try {
            System.out.println("載入class Some");
            Class c = Class.forName("com.openhome.reflect.Some");

            System.out.println("宣告參數");
            Some some = null;

            System.out.println("建立物件");
            some = new Some();
            return c;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class laterInit() {

        try {
            System.out.println("載入class Some");
            Class c = Class.forName("com.openhome.reflect.Some",
                    false,
                    Thread.currentThread().getContextClassLoader());

            System.out.println("宣告參數");
            Some some = null;

            System.out.println("建立物件");
            some = new Some();
            return c;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


class Some {
    static {
        System.out.println("[執行靜態區域]");
    }
}
