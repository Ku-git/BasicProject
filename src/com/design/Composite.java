package com.design;

import java.util.ArrayList;
import java.util.List;

public class Composite {

    /**
     * Composite 設計模式，也被稱為組合模式，是一種結構型設計模式。
     * 它允許你將物件組合成樹狀結構來表示「部分-整體」的層次結構。
     * 這種模式使得客戶可以對單個物件和物件的組合一視同仁。
     * point:
     *  Composite 設計模式的核心是將物件組合成樹形結構。
     *  它使用了遞迴的方式來處理物件，這意味著一個 Composite 物件可以包含
     *  多個子 Composite 物件或 Leaf 物件（基本物件），
     *  而每個子 Composite 物件也可以有自己的子物件，形成一棵樹
     * 關鍵組件:
     *  Component：定義所有物件的統一接口。
     *  Leaf：表示樹的葉子節點，無法再有子節點的物件。
     *  Composite：表示樹的非葉子節點，內部可以包含其他 Composite 或 Leaf。
     * 應用場景:
     *  需要表示物件的層次結構時，例如組織結構樹、文件系統目錄。
     *  需要讓客戶以統一的方式處理單個物件和物件組合時。
     *  主要特點:
     *      一致性：對單個物件和組合物件的操作一致。
     *      靈活性：允許樹狀結構中添加新類型的組件，而不需要修改現有代碼。
     *      可擴展性：便於擴展和維護。
     */
    public static void main(String[] args) {

        Directory root = new Directory("root");
        Directory subDir1 = new Directory("subDir1");
        Directory subDir2 = new Directory("subDir2");

        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        File file3 = new File("file3.json");

        root.addComponent(subDir1);
        root.addComponent(subDir2);
        root.addComponent(file1);

        subDir1.addComponent(file2);
        subDir2.addComponent(file3);

        root.showDetail();
    }
}

interface FileSystemComponent {
    void showDetail();
}

//Leaf
class File implements FileSystemComponent {

    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetail() {
        System.out.println("File: " + name);
    }
}

// Composite 節點
class Directory implements FileSystemComponent {

    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetail() {
        System.out.println("Directory: " + name);
        for(FileSystemComponent component: components) {
            component.showDetail();
        }
    }

}
