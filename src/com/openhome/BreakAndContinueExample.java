package com.openhome;

public class BreakAndContinueExample {

    public static void main(String[] args) {

//        breakExample();

        continueExample();
    }

    private static void breakExample() {
        BACK: {
            for(int i = 0; i < 10; i++) {
                if(i == 5) {
                    break BACK;
                }
                System.out.println(i);
            }
            System.out.println("Test");
        }
    }

    private static void continueExample() {
        BACK1:
            for(int i = 0; i < 10; i++) {
                BACK2:
                    for(int j = 0; j < 10; j++) {
                        if(j == 5) {
                            continue BACK2;
                        }
                        System.out.println("j: " + j);
                    }
                    System.out.println("i: " + i);
                System.out.println("test");
            }

    }
}
