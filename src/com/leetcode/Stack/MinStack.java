package com.leetcode.Stack;

import java.util.ArrayDeque;

public class MinStack {

    private ArrayDeque<Integer> stack;

    private ArrayDeque<Integer> minStack;

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
    }

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.push(val);

        if(minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
       int val = stack.pop();

       if (val == minStack.peek()) {
           minStack.pop();
       }
    }

    public int top() {
        return stack.peek() == null? 0: stack.peek();
    }

    public int getMin() {
        return minStack.peek() == null? 0: minStack.peek();
    }
}
