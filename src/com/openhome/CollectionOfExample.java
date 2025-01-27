package com.openhome;

import java.util.List;

public class CollectionOfExample {


    public static void main(String[] args) {
        Student student = new Student();
        student.name = "sample";

        List<Student> students = List.of(student);
        System.out.println(students.get(0).name);
        students.add(new Student());

        student.name = "example";
        System.out.println(students.get(0).name);
    }

    static class Student {
        String name;

    }
}
