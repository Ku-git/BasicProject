package com.leetcode;

public class PalindromeNumber {

    public static void main(String[] args) {

        int input = 121;
        System.out.println(isPalindrome(input));
    }

    /**
     * 轉成字串並拆分出char array, 並從第一個與最後一個比對,
     * 依序漸進比對每個值,比對到中間的index代表前後都比對完了
     * return true or false
     */
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
