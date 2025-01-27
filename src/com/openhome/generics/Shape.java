package com.openhome.generics;

public interface Shape {
    double area();
}

record Circle(double x, double y, double radius) implements Shape {

    @Override
    public double area() {
        return radius * radius * Math.PI;
    }
}

record Square(double x, double y, double length) implements Shape {

    @Override
    public double area() {
        return length * length;
    }
}