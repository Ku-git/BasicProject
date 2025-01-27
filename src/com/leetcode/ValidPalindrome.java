package com.leetcode;

public class ValidPalindrome {

    public static void main(String[] args) {

        ValidPalindrome method = new ValidPalindrome();
        String input = "A man, a plan, a canal: Panama";
        boolean result = method.isPalindrome(input);
        System.out.println(result);
    }

    public boolean isPalindrome(String s) {

        int from = 0;
        int end = s.length() - 1;
        s = s.toLowerCase();
        while(from < end) {
            char fromCh = s.charAt(from);
            char endCh = s.charAt(end);
            if(!isChar(fromCh)) {
                from++;
                continue;
            } else if(!isChar(endCh)) {
                end--;
                continue;
            }

            if(fromCh != endCh) {
                return false;
            }
            from++;
            end--;
        }

        return true;
    }

    private boolean isChar(char ch) {

        return (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z');
    }
}
