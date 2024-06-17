package com.design;

import java.util.ArrayList;
import java.util.List;

public class Command {

    /**
     * 設計理念: 定義／執行
     *  測試人員定義測試案例，你撰寫的 TestRunner 執行測試案例，定義與執行是分離的，
     *  Gof 將此概念命名為 Command 模式。
     * Java thread & runnable:
     * 定義指令（Runnable），執行指令（Runnable）的是 Thread。
     * ex:
     *  Runnable runnable = () -> {
     * 	    你的流程
     *  };
     *  new Thread(runnable).start();
     * 相似點: composite & command
     * 〈Composite〉的範例中，網頁中Material 與 Playlist 就組合來看是 Composite
     *  自行撰寫的範例中，file & directory就是 composite，
     *  而fileSystemComponent & file(or Directory)又是command實現
     * ，就「定義／執行」的行為關係，就是 Command 的實現。
     *  雖然composite模式和command模式在結構和目的上有明顯的不同，但它們在某些行為上確實可以表現出相似性
     * reference: https://openhome.cc/zh-tw/pattern/structural/composite/
     * another point:
     *  也可以有其他角度的看法，例如，某個設計就「定義／執行」的行為關係是 Command 的實現，
     *  然而就「抽換策略」的行為來看，或許就是 Strategy，
     *  然後就「XX」的角度來看，可能又是 GGYY 模式…模式有時候想表達的是一種思考角度，
     *  有時候甚至想傳達的是某個問題情境。
     */
    public static void main(String[] args) {
        TestRunner runner = new TestRunner();
        runner.add(new CalculatorMinusTest());
        runner.add(new CalculatorPlusTest());
        runner.run();
    }
}

class Assert {

    public static void assertEquals(int expected, int result) {
        if(expected == result) {
            System.out.println("correct");
        } else {
            System.out.printf("failed! expected: %s, but it's %s\n", expected, result);
        }
    }
}

interface Test {
    public void run();
}

class TestRunner {
    private List<Test> tests = new ArrayList<>();

    public void add(Test test) {
        tests.add(test);
    }

    public void run() {
        for(Test test: tests) {
            test.run();
        }
    }
}

class CalculatorPlusTest implements Test {

    @Override
    public void run() {

        var calculator = new Calculator();
        var expected = 3;
        var result = calculator.add(1, 2);
        Assert.assertEquals(expected, result);
    }
}

class CalculatorMinusTest implements Test {

    @Override
    public void run() {

        var calculator = new Calculator();
        var expected = 0;
        var result = calculator.minus(1, 2);
        Assert.assertEquals(expected, result);
    }
}

class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int minus(int a, int b) {
        return a - b;
    }
}
