package com.openhome;

import java.util.ArrayList;
import java.util.List;

public class ReferenceExample {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        reassignArray(arr);
        System.out.println(arr[0]); // 輸出 1，原變數未改變

       var list = new ArrayList<Integer>();
       list.add(1);
       list.add(2);
       list.add(3);
       reassignArray2(list);
        System.out.println(list.get(0));
    }

    public static void reassignArray(int[] array) {
        array = new int[]{4, 5, 6};
    }

    public static void reassignArray2(List<Integer> list) {
        list = new ArrayList();
        list.add(4);
        list.add(5);
        list.add(6);
    }
}
