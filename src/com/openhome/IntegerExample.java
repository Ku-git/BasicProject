package com.openhome;

public class IntegerExample {

    public static void main(String[] args) {
        Integer i = 100;
        Integer j = 100;
        System.out.println(i == j);

        Integer i2 = 200;
        Integer j2 = 200;
        System.out.println(i2 == j2);

        int sum = 0;
        for(String arg : args) {
            sum += Integer.parseInt(arg);
        }
        System.out.println(sum);

    }
}
