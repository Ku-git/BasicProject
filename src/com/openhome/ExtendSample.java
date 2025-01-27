package com.openhome;

public class ExtendSample {

    public static void main(String[] args) {
        Role role1 = new SwordMan();
        drawFight(role1);
        System.out.println(Role.getSecretItem());
        SwordMan swordMan = new SwordMan();
        drawFight(swordMan);
        System.out.println(swordMan);
        System.out.println(SwordMan.getSecretItem());
        Magician magician = new Magician();
        drawFight(magician);
        System.out.println(magician);
        System.out.println(Magician.getSecretItem());
    }

    public static void drawFight(Role role) {
        role.fight();
    }

    static class Role {

        private static String secretItem = "role Secret";
        private String name;
        private int level;

        public Role() {
            System.out.println("role init");
        }

        public void fight() {
            System.out.println("do nothing");
        }

        public static String getSecretItem() {
            return secretItem;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

    static class SwordMan extends Role {

        private static String secretItem;

        public SwordMan() {
            System.out.println("sword man init");
        }

        public void fight() {
            System.out.println("attack with sword");
        }

        public static String getSecretItem() {
            return secretItem;
        }

        @Override
        public String toString() {
            return "SwordMan " + this.getName() + ", level: " + this.getLevel() + ", blood: " + this.getLevel();
        }
    }

    static class Magician extends Role {

        private static String secretItem = "Magician";

        public Magician() {
            System.out.println("magician init");
        }

        public void fight() {
            System.out.println("attack with magic");
        }

        public static String getSecretItem() {
            return secretItem;
        }

        @Override
        public String toString() {
            return "Magician " + this.getName() + ", level: " + this.getLevel() + ", blood: " + this.getLevel();
        }
    }

}
