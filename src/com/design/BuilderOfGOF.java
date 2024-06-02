package com.design;

public class BuilderOfGOF {

    /**
     * 目的: 分離流程／表現
     * 關注點是在Builder的可替換性
     * 設計原則
     * 1. 單一職責原則：將複雜對象的構建和表示分離。
     * 2. 開放封閉原則：可以在不修改已有代碼的情況下引入新的建造方法
     */
    public static void main(String[] args) {

        Maze maze = new Maze();
        maze.generate()
            .render(new SimpleMazeBuilder());
    }
}

class Maze {
    final static int R = 0;
    final static int O = 1;
    final static int T = 2;

    private int[][] cells;

    Maze generate() {
        cells = new int[][]{
                {O, O, O, O, O, O, O},
                {O, R, R, R, R, T, O},
                {O, R, O, R, O, R, O},
                {O, R, T, O, R, O, O},
                {O, O, R, O, R, O, O},
                {O, R, R, T, R, R, O},
                {O, O, O, O, O, O, O}
        };
        return this;
    }

    void render(MazeBuilder builder) {

        for(var row: cells) {
            for(var cell: row) {
                switch(cell) {
                    case R -> builder.road();
                    case O -> builder.obstacle();
                    case T -> builder.treasure();
                }
            }
            builder.nextRow();
        }
        System.out.println(builder.build());
    }
}

interface MazeBuilder {

    void road();
    void obstacle();
    void treasure();
    void nextRow();
    String build();
}

class SimpleMazeBuilder implements MazeBuilder {

    private final StringBuffer buffer = new StringBuffer();

    @Override
    public void road() {
        buffer.append(" ");
    }

    @Override
    public void obstacle() {
        buffer.append("x");
    }

    @Override
    public void treasure() {
        buffer.append("$");
    }

    @Override
    public void nextRow() {
        buffer.append("\n");
    }

    @Override
    public String build() {
        return buffer.toString();
    }
}
