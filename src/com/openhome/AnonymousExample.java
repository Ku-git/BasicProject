package com.openhome;

public class AnonymousExample {

    public static void main(String[] args) {

        Object obj = new Object() {
            @Override
            public String toString() {
                return "Hello World";
            }
        };

        System.out.println(obj);

        AnonymousInterface anonymous = () -> "hello world lambda";

        System.out.println(anonymous.toDo());
    }

    public interface AnonymousInterface {

        public String toDo();
    }
}