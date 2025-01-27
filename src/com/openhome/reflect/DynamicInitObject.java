package com.openhome.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DynamicInitObject {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class clz = Class.forName("com.openhome.reflect.Student");
        System.out.println("belong module: " + clz.getModule());
        //指定參數型態
        Class[] params = {String.class, Integer.TYPE};
        Constructor constructor = clz.getConstructor(params);
        //指定參數內容
        Object[] paramObj = {"Edge", 18};

        //實例化 Object obj = clz.newInstance(); is deprecated in java9
        Object object = constructor.newInstance(paramObj);
        System.out.println(object);

        Student[] students = (Student[]) Array.newInstance(clz, 5);
        System.out.println(Arrays.toString(students));

        Method setter = clz.getMethod("setName", new Class[]{String.class});
        setter.invoke(object, new Object[]{"Rex"});

        Method getter = clz.getMethod("getName");
        System.out.println(getter.invoke(object));
    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
