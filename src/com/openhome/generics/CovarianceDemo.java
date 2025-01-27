package com.openhome.generics;

public class CovarianceDemo {

    /**
     * 測試Java泛型模擬共變性(covariance)
     * 共變性: 若B是A的次型態，而Node<B>也視為Node<A>的次型態，
     * 因為Node保持了次型態的關係，稱Node具有共變性
     * ps. java的泛型不具有共變性
     */
    public static void main(String[] args) {
        var c1 = new Node<>(new Circle(0, 0, 10), null);
        var c2 = new Node<>(new Circle(0, 0, 20), c1);
        var c3 = new Node<>(new Circle(0, 0, 30), c2);

        var s1 = new Node<>(new Square(0, 0, 15), null);
        var s2 = new Node<>(new Square(0, 0, 30), s1);

        Node<? extends Shape> node = new Node<>(new Circle(0,0, 10), null);
        Object o = node.value;
        Circle o1 = (Circle) node.value;
        System.out.println(o1);
        node.value = null;
        Node<? super Circle> node2 = new Node<>(new Circle(0,0, 10), null);

        Circle o3 = (Circle) node2.value;
        System.out.println(o3.area());

//        show(c3);
//        show(s2);
    }

    public static void show(Node<? extends Shape> n) {
        Node<? extends Shape> node = n;
        do {
            System.out.println(node.value);
            node = node.next;
        } while (node != null);
    }
}
