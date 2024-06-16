package com.design;

public class Iterator {

    /**
     * 在 Gof 中，稱這樣的模式為 Iterator，因為逐一走訪的需求太常見，
     * 許多語言都會規範如何傳回 iterator，
     * 並提供對應的語法支援，例如 Java 規範了 Iterable，若物件實現了該介面，
     * 就可以搭配 for 來走訪元素，例如 List 本身就是 Iterable 的子介面
     */
    public static void main(String[] args) {
        CustomArrayList list = new CustomArrayList(3);
        list.add("A");
        list.add("B");
        list.add("C");

        printAll(list.iterator());
    }

    static void printAll(java.util.Iterator it) {
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

class CustomArrayList {
    private Object[] elements;
    private int index;

    public CustomArrayList(int size) {
        elements = new Object[size];
    }

    public void add(Object o) {
        elements[index++] = o;
    }

    private class IteratorImpl implements java.util.Iterator {
        private int index;

        @Override
        public boolean hasNext() {
            return index < elements.length;
        }

        @Override
        public Object next() {
            return elements[index++];
        }

    }

    public java.util.Iterator iterator() {
        return new IteratorImpl();
    }
}
