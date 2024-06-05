package com.design;

public class Prototype {

    /**
     * 原型模式（Prototype Pattern）是一種創建型設計模式，它允許一個物件通過拷貝已有的物件來創建新物件，
     * 而不是通過使用 new 關鍵字來實例化。這樣可以提高創建物件的效率，
     * 特別是在創建物件的過程中涉及到複雜的初始化邏輯時。
     * 原型模式通常用在需要頻繁創建相同或相似對象的情況下
     * 優點:
     *  性能提高：通過複製已有物件來創建新物件，比通過構造函數來創建物件性能更高，特別是當物件的創建成本高昂時。
     *  簡化物件創建：避免了通過複雜的初始化邏輯來創建新物件，直接複製已有物件可以簡化這一過程。
     *  動態構造物件：在運行時期可以動態構造物件，不需要提前知道要創建的物件型別。
     * 缺點:
     *  深拷貝和淺拷貝：實現深拷貝需要額外的努力和代碼，特別是當物件中包含了對其他物件的引用。
     *  複製方法的實作：每個類別都需要實作 clone 方法，增加了代碼的複雜性。
     * 使用場景:
     *  物件創建開銷大的情況：當創建一個物件的開銷非常大時，可以通過複製一個已經存在的物件來創建新物件。
     *  需要頻繁創建相似物件的情況：當需要頻繁創建相似物件時，可以通過原型模式來簡化這一過程。
     *  動態指定創建物件的情況：在運行時期動態指定需要創建的物件，可以通過原型模式來實現。
     */
    public static void main(String[] args) {

        PrototypeObject prototype = new ConcretePrototype("prototype field");

        PrototypeObject clone1 = prototype.clone();
        PrototypeObject clone2 = prototype.clone();
        System.out.println(prototype);
        System.out.println(clone1);
        System.out.println(clone2);

        System.out.println(prototype == clone1);
        System.out.println(prototype == clone2);
        System.out.println(clone1 == clone2);

        ShadowInnerObj innerObj = new ShadowInnerObj("shadow inner field");
        ShadowObject shadowObj = new ShadowObject(innerObj);
        ShadowObject shadow1 = (ShadowObject) shadowObj.clone();
        ShadowObject shadow2 = (ShadowObject) shadowObj.clone();

        shadow1.innerObj.setField("shadow 1 field");

        System.out.println(shadow1);
        System.out.println(shadow2);

        System.out.println(shadowObj == shadow1);
        System.out.println(shadowObj == shadow2);
        System.out.println(shadow1 == shadow2);
    }
}

interface PrototypeObject {
    PrototypeObject clone();
}

class ConcretePrototype implements PrototypeObject {

    String field;

    public ConcretePrototype(String field) {
        this.field = field;
    }

    @Override
    public PrototypeObject clone() {
        return new ConcretePrototype(this.field);
    }

    @Override
    public String toString() {
        return "ConcretePrototype{" +
                "field='" + field + '\'' +
                '}';
    }
}

class ShadowObject implements Cloneable {

    public ShadowInnerObj innerObj;

    public ShadowObject(ShadowInnerObj innerObj) {
        this.innerObj = innerObj;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "ShadowObject{" +
                "innerObj=" + innerObj +
                ", memoryLocation=" + System.identityHashCode(this) +
                '}';
    }
}

class ShadowInnerObj {
    private String field;

    public ShadowInnerObj(String field) {
        this.field = field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "ShadowInnerObj{" +
                "field='" + field + '\'' +
                ", memoryLocation=" + System.identityHashCode(this) +
                '}';
    }
}
