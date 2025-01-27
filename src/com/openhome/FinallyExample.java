package com.openhome;

public class FinallyExample {

    public static void main(String[] args) {
        System.out.println(test(true));
    }

    public static int test(boolean flag) {
        try {
            if(flag) {
                return 1;
            }
        } finally {
            System.out.println("finally");
        }
        return 0;
    }
}
