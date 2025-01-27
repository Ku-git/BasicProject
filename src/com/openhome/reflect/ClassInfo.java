package com.openhome.reflect;

public class ClassInfo {

    public static void main(String[] args) {
        Class clazz = String.class;

        System.out.println("class name: " + clazz.getName());
        System.out.println("is interface: " + clazz.isInterface());
        System.out.println("is primitive: " + clazz.isPrimitive());
        System.out.println("is array: " + clazz.isArray());
        System.out.println("super class name: " + clazz.getSuperclass().getName());
        System.out.println("belong module: " + clazz.getModule());
        //JVM只用一個Class實例代表一個.class檔案
        //準確來說，經由同一類別載入器載入的.class檔案
        System.out.println("".getClass() == clazz);
    }
}
