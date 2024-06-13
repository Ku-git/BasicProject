package com.design;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public class Flyweight {

    /**
     * Flyweight 設計模式是一種結構型設計模式，主要目的是通過共享來有效地支持大量細粒度的對象。
     * 該模式通過共享相同的狀態（即內部狀態），從而減少記憶體消耗，優化性能。
     * scenario:
     *  當應用中需要大量創建相同或類似的對象，並且這些對象會佔用大量記憶體時。
     *  當對象的內部狀態可以被多個對象共享，並且能夠將內部狀態和外部狀態分開時。
     *  當應用程序需要支持大量對象但受限於記憶體資源時。
     *  ps. WeakHashMap，可在記憶體不足時，主動釋放未被程式其他部份參考的物件
     */
    public static void main(String[] args) {

        Font font1 = FontFactory.get("細明體", Style.ITALIC, 10);
        Font font2 = FontFactory.get("細明體", Style.ITALIC, 10);
        Font font3 = FontFactory.get("細明體", Style.ITALIC, 11);
        System.out.println(font1 == font2);
        System.out.println(System.identityHashCode(font1));//memory address
        System.out.println(System.identityHashCode(font2));//memory address
        System.out.println(font1 == font3);

        Font font4 = new Font("test", Style.BOLD, 10);
        Font font5 = new Font("test", Style.BOLD, 10);
        System.out.println(System.identityHashCode(font4));
        System.out.println(System.identityHashCode(font5));
    }
}

enum Style {
    PLAIN, BOLD, ITALIC, UNDERLINED;
}

/**
 * 使用 record 時，hashCode 和 equals 方法會自動基於欄位生成。
 * record 與普通類別的區別
 * 簡化代碼：record 簡化了基於欄位的對象比較過程，不需要手動重寫 hashCode 和 equals 方法。
 * 功能聚焦：record 專注於存儲不可變數據，非常適合用於需要基於欄位比較的情境，如 FontFactory
 * ps. 若使用class需要自行實作hashCode & equals
 */
record Font(String name, Style style, int size) {}

/**
 * WeakHashMap 來儲存 Font 實例。只要相同參數的 Font 已經存在於地圖中，
 * FontFactory.get 就會返回該實例，而不是創建一個新的。
 * point:
 *  FontFactory 使用 WeakHashMap 來儲存 Font 實例，這是一種基於鍵（在這裡是 Font）的弱參考（WeakReference）。
 *  這意味著： 如果地圖中的 Font 鍵不再被其他強參考引用，垃圾回收器可以回收這些鍵，並且相應的 Font 實例也會被回收。
 */
class FontFactory {
    private static Map<Font, WeakReference<Font>> fontMap = new WeakHashMap<>();

    public static Font get(String name, Style style, int size) {

        Font font = new Font(name, style, size);
        if(!fontMap.containsKey(font)) {
            fontMap.put(font, new WeakReference<>(font));
        }
        return fontMap.get(font).get();
    }

}