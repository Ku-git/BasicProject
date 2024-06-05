package com.design;

public class Decorator {

    /**
     * 裝飾器模式是一種結構型模式，它允許你動態地將新功能附加到對象上。
     * 這種模式的主要思想是將類別的物件包裝在裝飾器類別中，從而擴展原始類別的功能
     * point:
     *  1. 這樣的設計是組合優於繼承（composite over inheritance）的實現
     *  2. 可依據需求疊加功能，保持其接口的完整性
     *  3. 高度的靈活性和可擴展性
     *  4. 符合開放封閉原則
     * native example:
     * 在 java.io 套件中，有些輸入輸出的功能修飾，就是採用 Decorator 來實現
     * FileReader 沒有緩衝區處理的功能，可以由 BufferedReader 提供，BufferedReader沒有改變 FileReader 的功能，
     * 是在既有 FileReader 的操作成果上再做加工，而 BufferedReader 也不只可以用於 FileReader，
     * 只要是 Reader 的子類別，都可以套用 BufferedReader。
     * 也就是說，功能可以視需求而堆疊
     */
    public static void main(String[] args) {

        Coffee coffee = new BlackCoffee();
        System.out.println(coffee.make());

        coffee = new WithMilk(coffee);
        System.out.println(coffee.make());

        coffee = new WithSugar(coffee);
        System.out.println(coffee.make());
    }
}

//Coffee 是組件
interface Coffee {
    String make();
}

//SimpleCoffee 是具體組件
class BlackCoffee implements Coffee {

    @Override
    public String make() {
        return "black coffee";
    }
}

//CoffeeDecorator 是裝飾器
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String make() {
        return coffee.make();
    }
}

//WithMilk 和 WithSugar 是具體裝飾器
class WithMilk extends CoffeeDecorator {

    public WithMilk(Coffee coffee) {
        super(coffee);
    }

    public String make() {
        return coffee.make() + " with milk";
    }
}

class WithSugar extends CoffeeDecorator {

    public WithSugar(Coffee coffee) {
        super(coffee);
    }

    public String make() {
        return coffee.make() + " with sugar";
    }
}

