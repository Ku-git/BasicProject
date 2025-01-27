package com.openhome.lambda;

public class DefaultMethodDemo {

    public static void main(String[] args) {
        Ball pingpong = new Ball();
        pingpong.radius = 3;

        Ball tennis = new Ball();
        tennis.radius = 5;

        System.out.println(pingpong.lessThan(tennis));
        System.out.println(pingpong.greaterThan(tennis));
        System.out.println(pingpong.getDefaultSize());
    }

    interface Comparable<T> {
        int compareTo(T o);

        default boolean lessThan(T o) {
            return compareTo(o) < 0;
        }

        default boolean greaterThan(T o) {
            return compareTo(o) > 0;
        }

        default boolean lessOrEqual(T o) {
            return compareTo(o) <= 0;
        }

        default double getDefaultSize() {
            double size = getRadius() * Math.PI;
            return size;
        }

        private double getRadius() {
            return 1.0;
        }
    }

    static class Ball implements Comparable<Ball> {

        private int radius;

        @Override
        public int compareTo(Ball o) {
            return this.radius - o.radius;
        }

    }
}
