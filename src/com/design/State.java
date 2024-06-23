package com.design;

import java.util.Map;
import java.util.function.Consumer;

public class State {

    /**
     * State 模式（State Pattern）是一種行為設計模式，允許對象在其內部狀態改變時改變其行為。
     * 該模式將狀態的行為封裝在獨立的狀態類中，並使得對象能夠根據內部狀態的變化來切換行為。
     * 這樣的設計使得對象在不同的狀態下具有不同的行為，而這些狀態和行為之間的變換是對象內部的細節，
     * 外部對象無需關心其內部的狀態變化。
     * 概念: 規則／狀態
     *  1. 狀態封裝：每個狀態都有其對應的類，這些類封裝了該狀態下的行為。
     *      這樣做的好處是，每個狀態的邏輯都獨立存在，易於管理和修改。
     *  2. 上下文類： 上下文類（Context）維護一個當前狀態的引用，並且對外提供操作方法。
     *  3. 狀態轉換： 狀態之間的轉換通常由狀態自身或者上下文類來完成。在狀態轉換時，上下文類會更改其當前狀態的引用。
     *  4. 分離狀態行為：通過將不同狀態的行為分離到不同的狀態類中，可以使得每個狀態的行為獨立且易於理解。
     *      這樣可以避免將所有狀態行為混合在一起造成的複雜性。
     *  another point:
     *      雖然這邊使用了 Java 的列舉，不過其實透過繼承關係，
     *      定義 Rule 作為 Fatal、Stable 與 Revivable 的父類別，也可以實現以上的概念。
     *  ps. 威康生命遊戲: https://openhome.cc/zh-tw/algorithm/basics/life/
     */
    public static void main(String[] args) {

        Cell[][] cells = cells(new int[][] {
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 1},
                {0, 1, 1, 1, 0, 0, 1, 0, 1, 1},
                {0, 1, 1, 1, 0, 1, 0, 0, 1, 1},
                {0, 1, 0, 1, 1, 0, 0, 1, 1, 1},
                {0, 1, 0, 1, 0, 1, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0, 1, 0, 1, 1},
                {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                {0, 1, 0, 1, 1, 0, 0, 0, 1, 1}});


        for(var g = 0; g < 35; g++) {
            print(cells);
            nextGen(cells);
        }
    }

    public static Cell[][] cells(int[][] rawData) {
        var cells = new Cell[rawData.length][rawData[0].length];
        for(var i = 0; i < rawData.length; i++) {
            for(var j = 0; j < rawData[i].length; j++) {
                cells[i][j] = new Cell(i, j, rawData[i][j]);
            }
        }
        return cells;
    }

    public static Cell[][] copy(Cell[][] source) {
        Cell[][] cells = new Cell[source.length][source[0].length];
        for(var i = 0; i < source.length; i++) {
            for(var j = 0; j < source[i].length; j++) {
                cells[i][j] = source[i][j].copy();
            }
        }
        return cells;
    }

    public static void nextGen(Cell[][] cells) {
        Cell[][] currentCells = copy(cells);
        for(var row : cells) {
            for(var cell : row) {
                cell.nextGen(currentCells);
            }
        }
    }

    public static void print(Cell[][] cells) {
        System.out.println("Status...");
        for(var row : cells) {
            for(var cell : row) {
                System.out.print(cell.isAlive == 0 ? '~' : '*');
            }
            System.out.println();
        }
    }
}

//狀態轉移的職責由代表規則的物件執行
enum Rule implements Consumer<Cell> {
    FATAL {
        @Override
        public void accept(Cell cell) {
            cell.isAlive = 0;
        }
    },
    STABLE {
        @Override
        public void accept(Cell cell) {

        }
    },
    REVIVABLE {
        @Override
        public void accept(Cell cell) {

        }
    };

    private static Map<Integer, Rule> envs = Map.of(2, STABLE, 3, REVIVABLE);

    public static Rule of(Cell[][] cells, Cell cell) {

        //計算鄰居數
        int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1},
                {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        var count = 0;
        for (var i = 0; i < 8 && count < 4; i++) {
            var r = cell.i + dirs[i][0];
            var c = cell.j + dirs[i][1];
            if (r > -1 && r < cells.length && c > -1 && c < cells[0].length
                    && cells[r][c].isAlive != 0) {
                count++;
            }
        }
        return Rule.envs.getOrDefault(count, FATAL);
    }
}

class Cell {
    int i;
    int j;
    int isAlive;

    public Cell(int i, int j, int isAlive) {
        this.i = i;
        this.j = j;
        this.isAlive = isAlive;
    }

    /* 狀態轉移職責 -> 改由rule處理
      使用 switch（或 if/else）來判斷接下來要套用哪個規則，進行對應的狀態轉移，簡單而直覺；
        然而有時候在更複雜的遊戲裡，你可能會有更多的規則，並根據規則對角色進行對應的狀態轉移，
        若單純只用 switch（或 if/else）來處理，會過於冗長
    void nextGen(Cell[][] cells) {

        switch (Rule.of(cells, this)) {
            case FATAL -> this.isAlive = 0;
            case REVIVABLE -> this.isAlive = 1;
        }
    }*/
    void nextGen(Cell[][] cells) {
        Rule.of(cells, this).accept(this);
    }

    Cell copy() {
        return new Cell(i, j, isAlive);
    }
}
