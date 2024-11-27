package com.leetcode.Stack;

import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    private static final Map<Character, Character> parenthesesMap = Map.of('(', ')', '{', '}', '[', ']');

    public static void main(String[] args) {

        ValidParentheses method = new ValidParentheses();
        String input = "(){}}{";
        boolean result = method.isValid(input);
        System.out.println(result);
    }

    public boolean isValid(String s) {

        //長度一定是偶數
        if(s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for(char c: s.toCharArray()) {
            if(stack.isEmpty()) {
                stack.push(c);
                continue;
            }
            char top = stack.peek();
            if (parenthesesMap.containsKey(top) && parenthesesMap.get(top) == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
