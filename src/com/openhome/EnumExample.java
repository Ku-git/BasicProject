package com.openhome;

public class EnumExample {

    public static void main(String[] args) {
        play(Action.DOWN);
    }

    public static void play(Action action) {
        System.out.println(
                switch(action) {
                    case STOP -> "停止";
                    case RIGHT -> "右";
                    case LEFT -> "左";
                    case UP -> "上";
                    case DOWN -> "下";
                }
        );
    }
}


enum Action {
    STOP, RIGHT, LEFT, UP, DOWN;

    Action() {
    }

}