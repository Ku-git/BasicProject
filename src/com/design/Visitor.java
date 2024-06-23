package com.design;

public class Visitor {

    /**
     * 揭露結構／型態
     * Visitor（訪問者）模式是一種行為型設計模式，其主要目的是在不改變已有元素（即被訪問者）的情況下，
     * 對其結構（結構包含的屬性或方法）進行新功能的操作。
     * 這種模式主要解決了在不破壞封裝的前提下，對元素結構增加新的操作方式的需求。
     * another point:
     *  Gof 中，Func 的實現是被稱為 Visitor 的角色，不需要 instanceof 的原因在於，
     *  Func 實例在 Rectangle、Circle 的 apply 方法中造訪了 this，方法中的 this 本來就具備型態，
     *  編譯器知道要呼叫哪個 Func 實例的哪個 apply，也就是這個行為是在靜態時期就決定的，
     *  其實就是 Java 的重載應用。
     * prop:
     *  1. 分離關注點: Visitor 模式能夠將操作和數據結構分離開來。
     *      訪問者對元素結構的具體實現進行操作，而不需要修改這些元素本身的結構。
     *  2. 單一責任原則: 每個具體訪問者都實現了一個特定的操作，這符合單一責任原則。
     *  3. 開放/封閉原則: 新的訪問者可以通過實現訪問者接口來擴展系統的功能，而不需要修改現有的元素結構或訪問者的代碼。
     * cron:
     *  1. 增加新元素類型複雜：如果現有的訪問者類別需要訪問新的元素類型，
     *      就需要修改所有現有的訪問者類別，這樣會影響到系統的擴展性。
     *  2. 破壞封裝性: Visitor 模式在訪問者中添加操作不會改變元素本身的結構，但同時也破壞了元素的封裝性。
     *      訪問者需要訪問元素的內部狀態，這可能會使元素暴露太多細節給訪問者。
     */
    public static void main(String[] args) {
        var areaCalculator = new AreaCalculator();
        var rect = new RectangleObj(10, 20);
        var circle = new CircleObj(10);

        System.out.println(rect.apply(areaCalculator));
        System.out.println(circle.apply(areaCalculator));
    }
}

//Visitor
interface Func {

    //apply = visit, 針對rectangle的訪問
    double apply(RectangleObj rectangle);
    //apply = visit, 針對circle的訪問
    double apply(CircleObj circle);
}

interface ShapeObj {
    //定義了一些接受訪問者的方法
    double apply(Func func);
}

//結構物件（Object Structure）：即被訪問的元素的集合。
class RectangleObj implements ShapeObj {
    final double width;
    final double height;

    public RectangleObj(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double apply(Func func) {
        return func.apply(this);
    }
}

class CircleObj implements ShapeObj {
    final double radius;

    public CircleObj(double radius) {
        this.radius = radius;
    }

    @Override
    public double apply(Func func) {
        return func.apply(this);
    }
}

/*
原先需要使用instanceof來判斷型別後，再做處理
static double area(Shape shape) {
    if(shape instanceof Rectangle) {
        var rect = (Rectangle) shape;
        return rect.width * rect.height;
    }
    else if(shape instanceof Circle) {
        var circle = (Circle) shape;
        return circle.radius * circle.radius * Math.PI;
    }
    ...其他 else if 判斷 Shape 實例的真正型態後計算面積
}
 */
//具體訪問者（Concrete Visitor）：實現了訪問者接口，具體定義了對元素結構進行操作的方法
class AreaCalculator implements Func {

    @Override
    public double apply(RectangleObj rectangle) {
        return rectangle.height * rectangle.width;
    }

    @Override
    public double apply(CircleObj circle) {
        return circle.radius * circle.radius * Math.PI;
    }
}