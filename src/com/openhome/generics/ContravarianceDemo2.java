package com.openhome.generics;

import java.util.ArrayList;
import java.util.List;

public class ContravarianceDemo2 {

    /**
     * 這種用法主要用於寫入數據的情況，可以向該類型的變量添加 T 或 T 的子類型的對象
     * 使用情境
     *  寫入數據：
     *      當你希望向一個集合或資料結構中寫入數據，但不需要讀取該數據時，
     *      可以使用 <? super T>。這樣可以確保只允許添加 T 或 T 的子類型的對象。
     *      需要向多種父類型的集合中添加對象：
     *      當你有一個需要處理不同父類型的集合（如 List<Object> 或 List<Number>），
     *      並且你希望向這些集合中添加 T 或 T 的子類型的對象時，<? super T> 可以提供這樣的靈活性。
     *  靈活的數據結構處理：
     *      在設計 API 或庫時，使用 <? super T> 可以使你的方法更具通用性和靈活性，
     *      因為它可以接受更廣泛範圍的參數類型。
     *  協變性與逆變性：
     *      <? super T> 用於逆變性的場合，即你可以確保某個集合可以接受 T 及其子類型的實例，
     *      而不關心具體類型是什麼
     */
    public static void main(String[] args) {

        /*
         *  添加元素到集合
         * 可以將 Integer 添加到一個集合中，但不管這個集合是 List<Integer>、List<Number> 還是 List<Object>
         */
        List<Object> objectList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        addToList(objectList, 10); // 添加到 List<Object>
        addToList(numberList, 20); // 添加到 List<Number>
        addToList(integerList, 30); // 添加到 List<Integer>

        System.out.println(objectList); // 輸出: [10]
        System.out.println(numberList); // 輸出: [20]
        System.out.println(integerList); // 輸出: [30]

        /*
         * 使用通用方法進行元素處理
         * 可以處理任何 Number 或其子類型的列表，並將元素轉換為 Double
         */
        List<Number> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2.5);
        numbers.add(3L);

        processNumbers(numbers); // 輸出: 1.0, 2.5, 3.0

        /*
         * 擴展 API 的靈活性
         * addElements 方法可以接受任何接受 T 或其父類型的集合，
         * 並將元素添加到這些集合中，從而提供了靈活的數據處理能力。
         */
        List<Object> objects = new ArrayList<>();
        List<Number> nums = new ArrayList<>();

        addElements(objects, "Hello", 100, 3.14); // 添加不同類型對象到 List<Object>
        addElements(nums, 1, 2.5, 3L); // 添加不同數值到 List<Number>

        System.out.println(objects); // 輸出: [Hello, 100, 3.14]
        System.out.println(nums); // 輸出: [1, 2.5, 3]
    }

    public static void addToList(List<? super Integer> list, Integer value) {
        list.add(value);
    }

    public static void processNumbers(List<? super Number> list) {
        for (Object obj : list) {
            Number number = (Number) obj;
            System.out.println(number.doubleValue());
        }
    }

    public static <T> void addElements(List<? super T> list, T... elements) {
        for (T element : elements) {
            list.add(element);
        }
    }
}


