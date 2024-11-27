package com.leetcode.ArrayAndHashing;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

    public static void main(String[] args) {

        ValidSudoku method = new ValidSudoku();
        char[][] input =
               {{'.','.','.','.','5','.','.','1','.'},
                {'.','4','.','3','.','.','.','.','.'},
                {'.','.','.','.','.','3','.','.','1'},
                {'8','.','.','.','.','.','.','2','.'},
                {'.','.','2','.','7','.','.','.','.'},
                {'.','1','5','.','.','.','.','.','.'},
                {'.','.','.','.','.','2','.','.','.'},
                {'.','2','.','9','.','.','.','.','.'},
                {'.','.','4','.','.','.','.','.','.'}};
        boolean result = method.isValidSudoku(input);
        System.out.println(result);
    }

    public boolean isValidSudoku(char[][] board) {

        Set<Character> duplicateSet;
        //row
        for(int i = 0; i < board.length; i++) {

            duplicateSet = new HashSet<>();
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == '.') {
                    continue;
                }
                if(duplicateSet.contains(board[i][j])) {
                    System.out.printf("i = %d, j = %d, board = %c\n", i, j, board[i][j]);
                    return false;
                }
                duplicateSet.add(board[i][j]);
            }
        }
        //column
        duplicateSet = new HashSet<>();
        for(int i = 0; i < board.length; i++) {

            for(int j = 0; j < board[i].length; j++) {
                if(board[j][i] == '.') {
                    continue;
                }
                System.out.printf("i = %d, j = %d, board = %c\n", i, j, board[j][i]);
                if(duplicateSet.contains(board[j][i])) {
//                    System.out.printf("i = %d, j = %d, board = %c\n", i, j, board[j][i]);
                    return false;
                }
                duplicateSet.add(board[j][i]);
            }
        }
        //sub-box
        int x = 0;
        int y = 0;
        int limit = 3;
        while(x < 9 && y < 9) {

            for(int i = x; i < limit * (x/3 + 1) ; i++) {

                duplicateSet = new HashSet<>();
                for(int j = y; j < limit * (y/3 + 1); j++) {
                    if(board[i][j] == '.') {
                        continue;
                    }
                    if(duplicateSet.contains(board[i][j])) {
                        return false;
                    }
                    System.out.printf("i = %d, j = %d, board = %c\n", i, j, board[i][j]);
                    duplicateSet.add(board[i][j]);
                }
            }
            x += 3;
            if(x == board.length) {
                y += 3;
                x = 0;
            }

        }

        return true;
    }

}
