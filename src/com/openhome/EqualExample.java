package com.openhome;

import java.util.HashSet;
import java.util.Set;

public class EqualExample {

    public static void main(String[] args) {

        Set<Point> points = new HashSet<Point>();
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);
        points.add(p1);

        System.out.println(points.contains(p1));

        p1.x = 2;
        System.out.println(points.contains(p1));

    }

    static class Point {
        public int x;
        public final int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object that) {
            if(that instanceof Point) {
                Point p = (Point) that;
                return this.x == p.x && this.y == p.y;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return 41 * (41 + x) + y;
        }
    }
}
