package com.design;

import java.util.Iterator;

// Client code
public class Adapter {

    /**
     * Adapter（適配器）是一種設計模式，主要用於將一個類的接口轉換成客戶端所期望的另一個接口。
     * 這種模式允許原本由於接口不匹配而不能一起工作的類能夠協同工作。而通過組合（而非繼承）實現適配。
     * point:
     * 適配器模式通常用於以下情況：
     *  系統需要使用現有的類，但其接口與現有的系統不匹配。 這種情況可能是由於不同的代碼風格、不同的設計模式或不同的介面規範等原因。
     *  希望重用某個類，但是由於其接口不符合當前需求。 通過適配器，我們無需修改原始類的代碼，就可以使其與新的需求協同工作。
     * 適配器模式可以分為兩種主要類型：
     *  類適配器（Class Adapter）： 通過繼承來達到適配的目的。這意味著適配器類繼承了原始類，同時實現了目標接口。
     *  對象適配器（Object Adapter）： 通過組合來實現適配的目的。適配器類持有原始類的一個實例，同時實現了目標接口。
     * class adapter vs object adapter:
     *  如果單一繼承不是限制，並且可以修改被適配者類別，那麼類別適配器(class adapter)可能更簡單和直接。
     *  如果需要更大的靈活性，適應多個類別的能力，或者單一繼承是一個問題，使用包含合成的對象適配器(object adapter)是更靈活的解決方案。
     * another point:
     * 為什麼不直接繼承：
     *  違反合成優於繼承原則： 一般而言，合成優於繼承的原則建議使用合成（如對象適配器）通常更靈活，並且不太容易出現問題。
     *  代碼可重用性： 對象適配器可以通過包含不同被適配者的實例來實現多個類別的適配，這促進了代碼的可重用性。
     *  靈活性： 即使被適配者是final，或者無法修改其源代碼，對象適配器仍然可以適應類別。
     */
    public static void main(String[] args) {
        // Scenario 1: Using Class Adapter
        Printer classAdapter = new ClassAdapter();
        classAdapter.print("Hello from Class Adapter");

        // Scenario 2: Using Object Adapter
        LegacyPrinter legacyPrinter = new LegacyPrinter();
        Printer objectAdapter = new ObjectAdapter(legacyPrinter);
        objectAdapter.print("Hello from Object Adapter");

        for(char c: new IterableString("Hi iterator")) {
            System.out.println(c);
        }
    }
}

/**
 * 實際案例: iterator
 * 底層會是透過 iterator 取得 Iterator 後，呼叫 hasNext、next 來進行迭代，從展開後的程式碼（也就是客戶端）來看，
 * Iterator 實例就是 adapter 的角色，將基於索引的字串操作，對應至客戶端要求的介面。
 * 此範例是Object adapter
 */
class IterableString implements Iterable<Character> {
    private String str;

    IterableString(String str) {
        this.str = str;
    }

    public Iterator<Character> iterator() {
        return new Iterator<>() {
            private int index;

            public boolean hasNext() {
                return index < str.length();
            }

            public Character next() {
                return str.charAt(index++);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

/**
 * 在 Java 中實作介面，其實就是一種廣義的多重繼承，有時候你會需要繼承某類別的功能，來實現某個介面要求的行為
 * public class Adapter extends Adaptee implements Target {
 *     public void request() {
 *         specificRequest();
 *     }
 * }
 * 結構的角度來看，你就是將 Adaptee，組裝至要求 Target 行為的客戶端了；
 * 另一方面，由於 Java SE 8 以後，介面可以有預設實作方法，這也給了共用相同實作的方便性。
 * current example:
 * 每個 Ball 實例就會擁有 Comparable 定義的預設方法。因為類別可以實作多個介面，運用預設方法，
 * 就可以在某介面定義可共用的操作，若有個類別需要某些可共用操作，只需要實作相關介面，就可以混入（Mixin）這些共用的操作了
 * ex: 如果你需要繼承某類別，並混入一組具有預設方法的介面，也是在實現 Class Adapter 的概念了
 * public class Adapter extends Adaptee implements Target, IAdaptee1, Adaptee2 {
 *     public void request() {
 *         specificRequest();
 *     }
 * }
 */
class Ball implements Comparable<Ball> {

    private int radius;

    public int compareTo(Ball ball) {
        return this.radius - ball.radius;
    }
}

interface Comparable<T> {
    int compareTo(T that);

    default boolean lessThan(T that) {
        return compareTo(that) < 0;
    }
    default boolean lessOrEquals(T that) {
        return compareTo(that) <= 0;
    }
    default boolean greaterThan(T that) {
        return compareTo(that) > 0;
    }

}

//原先類別(被適配器 Adaptee)
class LegacyPrinter {
    void printMessage(String message) {
        System.out.println("legacy printer: " + message);
    }
}

// Printer interface (Target)
interface Printer {
    void print(String message);
}

// Class Adapter
class ClassAdapter extends LegacyPrinter implements Printer {
    @Override
    public void print(String message) {
        // Delegating to LegacyPrinter's printMessage method
        printMessage(message);
    }
}

// Object Adapter
class ObjectAdapter implements Printer {
    private LegacyPrinter legacyPrinter;

    public ObjectAdapter(LegacyPrinter legacyPrinter) {
        this.legacyPrinter = legacyPrinter;
    }

    @Override
    public void print(String message) {
        // Delegating to LegacyPrinter's printMessage method
        legacyPrinter.printMessage(message);
    }
}
