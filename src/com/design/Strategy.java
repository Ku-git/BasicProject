package com.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Strategy {

    /**
     * 策略模式（Strategy Pattern）是一種行為設計模式，旨在定義一系列算法，
     * 策略模式適用於系統需要動態選擇不同的算法或行為，且算法本身獨立於使用它的客戶端代碼。
     * 這種模式特別適合需要擴展性和靈活性的場景，如支付系統、排序算法、文件壓縮等。
     * 通過策略模式，可以將算法的實現細節從業務邏輯中解耦，使得系統更加模組化和易於維護。
     * 主要目的:
     *  1. 算法的獨立封裝：將不同的算法封裝在獨立的類中，使得這些算法可以相互替換。
     *  2. 開放-封閉原則：通過引入新的策略類，可以在不修改客戶端代碼的情況下擴展算法。
     *  3. 簡化複雜度：減少客戶端和算法的耦合，使得算法可以獨立變化。用策略模式來替代包含大量條件判斷的代碼，從而簡化代碼結構。
     */
    public static void main(String[] args) {
        var integers = Arrays.asList(10, 9, 1, 2, 5, 3, 8, 7);
        var sorted = new SortedIntegers();
        for(var integer : integers) {
            sorted.add(integer);
        }
        // [1, 2, 3, 5, 7, 8, 9, 10]
        System.out.println(sorted.integers());

        var descendingSorted = new SortedIntegers();
        for(var integer : integers) {
            descendingSorted.addDescending(integer);
        }
        // [1, 2, 3, 5, 7, 8, 9, 10]
        System.out.println(descendingSorted.integers());

        // 指定 Comparator 作為排序策略
        var ascendingSort = new SortedIntegers2(new AscendingStrategy());
        for(var integer: integers) {
           ascendingSort.add(integer);
        }
        System.out.println(ascendingSort.integers());
        var descendingSort = new SortedIntegers2(new DescendingStrategy());
        for(var integer: integers) {
            descendingSort.add(integer);
        }
        System.out.println(descendingSort.integers());

        //lambda
        var descendingSort2 = new SortedIntegers2((i, j) -> j - i);
        for(var integer: integers) {
            descendingSort2.add(integer);
        }
        System.out.println(descendingSort2.integers());
    }
}

interface IntegerComparator {
    int compare(Integer i, Integer j);
}

class AscendingStrategy implements IntegerComparator {

    @Override
    public int compare(Integer i, Integer j) {
        return i - j;
    }
}

class DescendingStrategy implements IntegerComparator {

    @Override
    public int compare(Integer i, Integer j) {
        return j - i;
    }
}

class SortedIntegers2 {

    private List<Integer> integers = new ArrayList<>();
    private IntegerComparator comparator;

    SortedIntegers2(IntegerComparator comparator) {
        this.comparator = comparator;
    }

    void add(Integer integer) {
        integers.add(integer);

        for (int i = 0; i < integers.size(); i++) {
            int to = insertTo(i);
            if (to != -1) {
                integers.add(to, integers.remove(i));
            }
        }
    }

    private int insertTo(int index) {
        Integer element = integers.get(index);
        for(int i = 0; i < index; i++) {
            if(comparator.compare(integers.get(i), element) > 0) {
                return i;
            }
        }
        return -1;
    }

    public List<Integer> integers() {
        return integers;
    }
}

//not using strategy, only using method to do ascending or descending
class SortedIntegers {

    private List<Integer> integers = new ArrayList<>();

    void add(Integer integer) {
        integers.add(integer);

        for (int i = 0; i < integers.size(); i++) {
            int to = insertTo(i);
            if (to != -1) {
                integers.add(to, integers.remove(i));
            }
        }
    }

    private int insertTo(int index) {
        Integer element = integers.get(index);
        for(int i = 0; i < index; i++) {
            if(integers.get(i) > element) {
                return i;
            }
        }
        return -1;
    }

    void addDescending(Integer integer) {
        integers.add(integer);

        for (int i = 0; i < integers.size(); i++) {
            int to = insertToDescending(i);
            if (to != -1) {
                integers.add(to, integers.remove(i));
            }
        }
    }

    private int insertToDescending(int index) {
        Integer element = integers.get(index);
        for(int i = 0; i < index; i++) {
            if(integers.get(i) < element) {
                return i;
            }
        }
        return -1;
    }

    public List<Integer> integers() {
        return integers;
    }
}



