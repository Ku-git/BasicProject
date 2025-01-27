package com.home;

import java.io.*;


class MyClass implements Serializable {
    private transient static int count = 10;
    private String name;

    public MyClass(String name) {
        this.name = name;
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}

public class SerializationExample {
    public static void main(String[] args) {
        MyClass obj = new MyClass("Example");

        // 將物件序列化到文件
        try {
            FileOutputStream fileOut = new FileOutputStream("object.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.println("物件已序列化");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 從文件中反序列化物件
        MyClass newObj = null;
        try {
            FileInputStream fileIn = new FileInputStream("object.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newObj = (MyClass) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("物件已反序列化");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // 檢查靜態變數的值是否被保留
        System.out.println("原始靜態變數值: " + MyClass.getCount());
        System.out.println("反序列化後的靜態變數值: " + newObj.getCount());
        System.out.println("反序列化後的物件名稱: " + newObj.getName());
    }
}
