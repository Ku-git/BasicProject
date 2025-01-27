package com.openhome;


public class StringExample {


    public static void main(String[] args) {
        String str = "ja" + "va";
        String str2 = "java";
        System.out.println(str == str2);

        String name1 = "ja";
        String name2 = name1 + "va";
        String name3 = "java";
        System.out.println(name2 == name3);

    }

}
