package com.design;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    /**
     * 解釋器模式（Interpreter Pattern） 是一種行為設計模式，
     * 用於解析和執行語言的表達式，通常用於編譯器或解釋器的設計中。
     * 它定義了一個語言的語法表示，並通過解釋器對這個語法進行解釋，實現對語言的解釋和執行
     * 何時使用解釋器模式:
     *  設計和實現一個特定語言：例如，編譯器、腳本語言的解釋器。
     *  需要對語法進行靈活的解釋和執行：例如，數學表達式求值、正則表達式匹配、命令模式的執行等。
     *  重複求解類似問題：例如，自動機、文本解析器
     * pros:
     *  1. 易於擴展：可以通過添加新的表達式類來擴展語法
     *  2. 可重複利用：表達式和解釋器可以在不同的上下文中重複使用。
     *  3. 易於理解和實現：對於語法簡單的語言，解釋器模式的結構很直觀。
     * cons:
     *  1. 性能問題：對於複雜的語法，會產生大量的表達式對象，導致性能下降
     *  2. 維護成本高: 語法變得複雜時，解釋器模式會導致類層次結構變得過於龐大和複雜。
     *      且變更時，需要進行相應的調整。
     */
    public static void main(String[] args) {

        var block = new Block();
        block.add(new Print("Justin"));
        block.add(new Print("Monica"));
        block.add(new Print("Irene"));
        block.execute();
        System.out.println("-------------");

        var repeatBlock = new Block();
        repeatBlock.add(
                new Repeat(3, new Print("dog"))
        );
        repeatBlock.execute();
        System.out.println("-------------");

        var main = new Block();
        // repeat 區塊
        var repeatBlocks = new Block();
        repeatBlocks.add(new Print("dog"));
        repeatBlocks.add(new Print("cat"));

        var repeat = new Repeat(3, repeatBlocks);
        main.add(repeat);

        main.execute();
        System.out.println("-------------");
    }

}


//定義抽象表達式（Abstract Expression）
interface ExpressCommand {
    void execute();
}

// print 指令
class Print implements ExpressCommand {
    private String text;

    Print(String text) {
        this.text = text;
    }

    public void execute() {
        System.out.println(text);
    }
}

// 指令區塊也是指令
class Block implements ExpressCommand {
    private List<ExpressCommand> cmdlt = new ArrayList<>();

    void add(ExpressCommand command) {
        cmdlt.add(command);
    }

    public void execute() {
        for(var command : cmdlt) {
            command.execute();
        }
    }
}

class Repeat implements ExpressCommand {
    private int times;
    private ExpressCommand command;

    Repeat(int times, ExpressCommand command) {
        this.times = times;
        this.command = command;
    }

    public void execute() {
        for(var i = 0; i < times; i++) {
            command.execute();
        }
    }
}

