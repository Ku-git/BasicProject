package com.design;

public class SimpleFactory {

    public static void main(String[] args) {
        Shape circle = ShapeFactory.getShape("Circle");
        circle.draw();

        Shape rectangle = ShapeFactory.getShape("Rectangle");
        rectangle.draw();

        Shape triangle = ShapeFactory.getShape("Triangle");
        triangle.draw();
    }

}

interface Shape {
    void draw();
}

class Circle implements Shape {

    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

class Triangle implements Shape {
    public void draw() {
        System.out.println("Drawing Triangle");
    }
}

/**
 * Point: 將創建過程封裝在一個單獨的工廠類別中, 不需要關注創建邏輯
 * 1. 關注分離(創建 & 使用)
 * 2. 減少重複代碼
 * 3. 集中管理
 * Description:
 * 通過一個工廠類來創建物件, 而不是直接實例化(new Object)
 * 工廠類(Factory class): 提供static method, 依據傳入的參數回傳不同類型的物件
 * 產品類(Product class): 為工廠創建的類別, 通常是同個父類別
 */
class ShapeFactory {

    public static Shape getShape(String shapeType) {
        if(shapeType == null) {
            return null;
        }
        return switch (shapeType.toUpperCase()) {
            case "CIRCLE" -> new Circle();
            case "RECTANGLE" -> new Rectangle();
            case "TRIANGLE" -> new Triangle();
            default -> null;
        };
    }
}
