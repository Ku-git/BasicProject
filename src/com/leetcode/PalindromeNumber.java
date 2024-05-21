package com.leetcode;

public class PalindromeNumber {

    public static void main(String[] args) {

        int input = 121;
        System.out.println(isPalindrome(input));
    }

    public static boolean isPalindrome(int x) {
        String input = String.valueOf(x);
        char[] chars = input.toCharArray();
        int stopPoint = chars.length/2;
        int index = 0;
        while (index < stopPoint) {
            if(chars[index] != chars[input.length() - index - 1]) {
                return false;
            }
            index++;
        }
        return true;
    }
}
