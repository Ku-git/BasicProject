package com.design;

public class FactoryMethod {

    public static void main(String[] args) {
        Pencil pencil = getPencil("Circle");
        assert pencil != null;
        pencil.drawPicture();
    }

    static Pencil getPencil(String shapeType) {
        return switch (shapeType.toUpperCase()) {
            case "CIRCLE" -> new CirclePencil();
            case "RECTANGLE" -> new RectanglePencil();
            default -> null;
        };
    }
}

/**
 * 工廠方法模式與簡單工廠模式都用於創建物件，但工廠方法模式強調依賴於抽象類別而非具體實現。
 * 功能依賴於抽象類別 Pencil，且使用 getPencil 方法封裝了創建過程。
 * 不需要知道具體的子類，只需依賴於抽象類別 Pencil 並調用 drawPicture 方法來完成繪圖操作。
 * 這樣的設計提高了系統的靈活性和可擴展性，同時遵循了面向對象設計原則。
 * Point:
 *  1. 工廠方法模式：
 *      (1) 透過定義一個創建物件的接口，讓子類來決定實例化哪個類。這樣，物件的實例化延遲到子類。
 *      (2) 工廠方法模式的核心在於提供了一個創建物件的方式，而不指定具體的類別名稱
 *  2. 開放-封閉原則（OCP）：系統應該對擴展開放，對修改封閉。通過增加新的 Pencil 子類，不需要修改現有的程式碼。
 *  3. 單一責任原則（SRP）：每個類別都有單一的職責：CirclePencil 和 RectanglePencil 負責提供對應形狀的繪圖方式。
 */
abstract class Pencil {

    public void drawPicture() {
        Shape shape = imageShapeToDraw();
        shape.draw();
    }

    protected abstract Shape imageShapeToDraw();
}

class CirclePencil extends Pencil {

    @Override
    protected Shape imageShapeToDraw() {
        return new Circle();
    }
}

class RectanglePencil extends Pencil {

    @Override
    protected Shape imageShapeToDraw() {
        return new Rectangle();
    }
}
