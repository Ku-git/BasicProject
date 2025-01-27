package com.openhome;

import java.io.*;

public class Member2Demo {

    public static void main(String[] args) throws Exception {
        Member2[] members = {new Member2("B1234", "Justin", 90),
                new Member2("B5678", "Monica", 95),
                new Member2("B9876", "Irene", 88)};
        for(var member : members) {
            member.save();
        }
        System.out.println(Member2.load("B1234"));
        System.out.println(Member2.load("B5678"));
        System.out.println(Member2.load("B9876"));
    }

    record Member2(String id, String name, int age) implements Serializable {

        public void save() throws IOException {
            try (var output = new ObjectOutputStream(new FileOutputStream(id))) {
                output.writeObject(this);
            }
        }

        public static Member2 load(String id)
                throws IOException, ClassNotFoundException {
            try (var input = new ObjectInputStream(new FileInputStream(id))) {
                return (Member2) input.readObject();
            }
        }
    }
}
