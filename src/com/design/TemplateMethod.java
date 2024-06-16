package com.design;

import java.io.IOException;
import java.util.Scanner;

public class TemplateMethod {

    /**
     * 從分離物件生成與使用的角度來看，IO 真正的生成實現推給子類別實作，可以歸為 Factory Method；
     * 然而從物件間的交流行為上來看，inputOutput 真正的行為實現推給子類別實作，可以歸為 Template Method。
     * point:
     *  思考的過程與 Factory Method 中的重構是類似的，
     *  現在應該可以更清楚地看出，print 與 nextInt 真正的行為實現推給子類別實作了，
     *  從原則的角度來看，出發點則是相依反轉，也就是 GuessGame 相依在抽象，而不是具體實現。
     * 特性:
     *  強烈的約束性:
     *      父類別規範主要流程，真正的行為實現推給子類別實作，是透過 Template Method 思考時的重點，
     *      另外還有個必須深思的點，Template Method 是繼承了父類別實作的流程，
     *      繼承具有強烈的約束性，具體來說就是有 is a 的關係
     * 使用情境:
     *  1. 你想主導大部份的流程，然而又想開放一點自訂的彈性
     *  2. 實作了某些通用的商務流程，然而細節部份，可以讓使用者自訂
     *  ex: Servlet API 的例子就貼近這個概念了，畢竟 HTTP 請求的處理流程有其複雜性，
     *      HttpServlet 實作了通用的部份，你只要自訂 doGet、doPost 等來完成需求就可以了。
     */
    public static void main(String[] args) {

        GuessGame3 game3 = new ConsoleGame2();
        game3.play();
    }


    static class GuessGame1 {

        public void play() {
            var console = new Scanner(System.in);
            var num = (Math.random() * 10);
            var guess = -1;
            do {
                System.out.println("輸入文字");
                guess = console.nextInt();
            } while (guess != num);
            System.out.println("猜中!");
        }
    }

    static abstract class GuessGame2 {

        public void play() throws IOException {

            Scanner scanner = inputOutput();
            var num = (Math.random() * 10);
            var guess = -1;
            do {
                System.out.println("輸入文字");
                guess = scanner.nextInt();
            } while (guess != num);
            System.out.println("猜中!");
        }

        public abstract Scanner inputOutput();
    }

    static class ConsoleGame extends GuessGame2 {

        @Override
        public Scanner inputOutput() {
            return new Scanner(System.in);
        }
    }

    abstract static class GuessGame3 {

        public void play() {
            var number = (int) (Math.random() * 10);
            var guess = -1;
            do {
                print("輸入數字：");
                guess = nextInt();
            } while(guess != number);
            print("猜中了");
        }

        public abstract void print(String text);
        public abstract int nextInt();
    }


    static class ConsoleGame2 extends GuessGame3 {

        private Scanner scanner = new Scanner(System.in);

        @Override
        public void print(String text) {
            System.out.println(text);
        }

        @Override
        public int nextInt() {
            return scanner.nextInt();
        }
    }


}
