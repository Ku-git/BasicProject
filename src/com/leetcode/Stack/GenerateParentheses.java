package com.leetcode.Stack;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {

        GenerateParentheses method = new GenerateParentheses();
        List<String> result = method.generateParenthesis(3);
        System.out.println(result);
    }

    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        doGenerate(result, new StringBuilder(), 0, 0, n);

        return result;
    }

    private void doGenerate(List<String> result, StringBuilder sb, int left, int right, int n) {

        if(left == n && right == n) {
            result.add(sb.toString());
            return;
        }

        if(left < n) {
            sb.append("(");
            doGenerate(result, sb, left + 1, right, n);
            sb.deleteCharAt(sb.length() - 1); //回傳結果後，刪除最末尾來回到當初處理的index
        }

        if(right < left) {
            sb.append(")");
            doGenerate(result, sb, left, right + 1, n);
            sb.deleteCharAt(sb.length() - 1); //回傳結果後，刪除最末尾來回到當初處理的index
        }

    }



}
