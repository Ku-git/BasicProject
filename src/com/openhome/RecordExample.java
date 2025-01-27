package com.openhome;

import java.util.List;

public class RecordExample {

    record Student(String name) {}

    public static void main(String[] args) {

        Student s1 = new Student("John");
        Student s2 = new Student("Jane");
        List<Student> students = List.of(s1, s2);
        System.out.println(students);

    }
}
