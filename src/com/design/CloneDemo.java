package com.design;

/**
 * 對應Prototype pattern中的優點額外描述
 */
public class CloneDemo {

    public static void main(String[] args) {
        long startTime, endTime;

        // 使用構造函數創建
        startTime = System.nanoTime();
        ExpensiveObject obj1 = new ExpensiveObject();
        endTime = System.nanoTime();
        System.out.println("Creation time using constructor: " + (endTime - startTime) + " ns");

        // 使用複製現有物件
        startTime = System.nanoTime();
        ExpensiveObject obj2 = new ExpensiveObject(obj1);
        endTime = System.nanoTime();
        System.out.println("Creation time using copy: " + (endTime - startTime) + " ns");

        // 使用 clone 方法
        startTime = System.nanoTime();
        ExpensiveObject obj3 = obj1.clone();
        endTime = System.nanoTime();
        System.out.println("Creation time using clone: " + (endTime - startTime) + " ns");
    }
}

class ExpensiveObject {
    private int[] data;

    public ExpensiveObject() {
        // 模擬一個昂貴的初始化過程
        this.data = new int[1000000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }
    }

    public ExpensiveObject(ExpensiveObject original) {
        // 使用淺拷貝
        this.data = original.data;
    }

    public ExpensiveObject clone() {
        // 深拷貝
        ExpensiveObject copy = new ExpensiveObject();
        System.arraycopy(this.data, 0, copy.data, 0, this.data.length);
        return copy;
    }
}
