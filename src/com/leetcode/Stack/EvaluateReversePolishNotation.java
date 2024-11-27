package com.leetcode.Stack;

import java.util.Stack;

public class EvaluateReversePolishNotation {

    public static void main(String[] args) {

        EvaluateReversePolishNotation method = new EvaluateReversePolishNotation();
        String[] input = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        int result = method.evalRPN(input);
        System.out.println(result);
    }

    public int evalRPN(String[] tokens) {

        Stack<Integer> numStack = new Stack<>();

        int result = 1;
        for(String token: tokens) {
            //is num
            if(!isToken(token)) {
                numStack.push(Integer.valueOf(token));
                continue;
            }
            int post = numStack.pop();
            int pre = numStack.pop();
            switch (token) {
                case "+" -> result = pre + post;
                case "-" -> result = pre - post;
                case "*" -> result = pre * post;
                case "/" -> result = pre / post;
            }
            numStack.push(result);
        }

        return numStack.pop();
    }

    private boolean isToken(String token) {

        return switch (token) {
            case "+", "-", "*", "/" -> true;
            default -> false;
        };
    }
}
