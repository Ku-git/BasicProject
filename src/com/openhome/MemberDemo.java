package com.openhome;

import java.io.*;

public class MemberDemo {

    public static void main(String[] args) throws IOException {
        Member[] members = {
                new Member("B1234", "Justin", 90),
                new Member("B5678", "Monica", 95),
                new Member("B9876", "Irene", 88)
        };
        for(var member : members) {
            member.save();
        }
        System.out.println(Member.load("B1234"));
        System.out.println(Member.load("B5678"));
        System.out.println(Member.load("B9876"));
    }

    record Member(String id, String name, int age) {
        public void save() throws IOException {
            try(var output = new DataOutputStream(new FileOutputStream(id))) {
                output.writeUTF(id);
                output.writeUTF(name);
                output.writeInt(age);
            }
        }

        public static Member load(String id) throws IOException {
            try(var input = new DataInputStream(new FileInputStream(id))) {
                return new Member(input.readUTF(), input.readUTF(), input.readInt());
            }
        }
    }
}
