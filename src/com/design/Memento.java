package com.design;

import java.util.Stack;

public class Memento {

    /**
     * 概念:
     *  Memento（備忘錄）模式是一種行為型設計模式，主要用於保存對象的狀態，
     *  從而可以在需要時恢復對象到先前的狀態。這種模式允許你在不破壞封裝性的前提下捕獲和恢復對象的內部狀態。
     *  它適用於需要重複撤銷（undo）和恢復（redo）操作的場景。
     * 使用情境:
     *  需要保存和恢復狀態：特別是當應用程序需要支持撤銷和重做功能時。
     *  需要記錄歷史狀態：需要跟踪對象狀態變化並能夠回到任意歷史狀態的應用場景，如版本控制系統。
     *  避免對象內部狀態的公開：希望在保存和恢復狀態時，避免暴露對象的內部結構和狀態。
     * pros:
     *  1. 保存對象狀態：允許在某個時刻保存對象的狀態，並能在未來的某個時刻恢復到該狀態。
     *  2. 不破壞封裝性：可以在不暴露對象內部結構的情況下捕獲和恢復對象的狀態。
     * cons:
     *  1. 記憶體開銷：如果需要保存對象的完整狀態，可能會佔用較多的記憶體。
     *  2. 恢復成本：對象的狀態恢復操作可能會比較耗時，特別是在狀態數據量很大的時候。
     */
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Caretaker caretaker = new Caretaker();

        editor.setContent("hello");
        System.out.println("current content:" + editor.getContent());
        caretaker.saveMemento(editor.createMemento());

        editor.setContent("Hello, World!");
        System.out.println("current content:" + editor.getContent());
        caretaker.saveMemento(editor.createMemento());

        editor.setContent("Hello, World! Welcome to the Memento Pattern.");
        System.out.println("Current Content: " + editor.getContent());

        editor.restore(caretaker.getLastMemento());
        System.out.println("after undo: " + editor.getContent());

        editor.restore(caretaker.getLastMemento());
        System.out.println("after undo: " + editor.getContent());
    }
}

// 發起者（Originator）類: 它是包含狀態的對象，能夠生成一個備忘錄（Memento），並且可以配合備忘錄恢復到之前的狀態。
class TextEditor {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Memento createMemento() {
        return new Memento(content);
    }

    public void restore(Memento memento) {
        content = memento.getContent();
    }

    // 備忘錄（Memento）類: 用於存儲發起者的內部狀態。備忘錄對其他對象是不可見的，只有發起者能訪問它。
    public static class Memento {
        private final String content;

        private Memento(String content) {
            this.content = content;
        }

        private String getContent() {
            return content;
        }
    }
}

// 管理者（Caretaker）類: 負責保存備忘錄，但不能修改備忘錄的內容。管理者知道何時創建和恢復備忘錄。
class Caretaker {
    private final Stack<TextEditor.Memento> mementos = new Stack<>();

    public void saveMemento(TextEditor.Memento memento) {
        mementos.push(memento);
    }

    public TextEditor.Memento getLastMemento() {
        if (!mementos.isEmpty()) {
            return mementos.pop();
        }
        return null;
    }
}