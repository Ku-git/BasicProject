package com.openhome.generics;

import java.util.Arrays;
import java.util.Comparator;

public class ContravarianceDemo {

    /**
     * 測試Java泛型模擬逆變性(Contravariance)
     * 逆變性: 若B是A的次型態，然而Node<A>卻被視為Node<B>的次型態，
     * 因為Node逆轉了次型態的關係，稱Node具有逆變性
     * ps. java的泛型不支援逆變性
     */
    public static void main(String[] args) {

        Comparator<Shape> areaComparator = (s1, s2) -> {
            var diff = s1.area() - s2.area();
            if(diff == 0) {
                return 0;
            }
            return diff > 0? 1: -1;
        };

        Comparator<Circle> circleComparator = (s1, s2) -> {
            var diff = s2.radius() - s1.radius();
            if(diff == 0) {
                return 0;
            }
            return diff > 0? 1: -1;
        };

        Comparator<Square> squareComparator = (s1, s2) -> {
            var diff = s2.length() + s1.length();
            if(diff == 0) {
                return 0;
            }
            return diff > 0? 1: -1;
        };

        var circles = new Group<Circle>(
                new Circle(0, 0, 10),
                new Circle(0, 0, 20)
                );
        Arrays.stream(circles.things).forEach(System.out::print);
        System.out.println();
//        circles.sort(areaComparator);
        circles.sort(circleComparator);
        Arrays.stream(circles.things).forEach(System.out::print);
        System.out.println();

        var squares = new Group<Square>(
                new Square(0, 0, 30),
                new Square(0, 0, 20)
               );
        Arrays.stream(squares.things).forEach(System.out::print);
        System.out.println();
        squares.sort(areaComparator);
        Arrays.stream(squares.things).forEach(System.out::print);
        System.out.println();
    }
}
