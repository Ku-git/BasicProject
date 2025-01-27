package com.leetcode.binarySearch;

public class Search2DMatrix {

    public static void main(String[] args) {

        Search2DMatrix method = new Search2DMatrix();
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 30;
        boolean result = method.searchMatrix(matrix, target);
        System.out.println(result);
    }

    /**
     * time complexity: O(log n * m)
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        int fromI = 0;
        int endI = matrix.length - 1;//n
        int fromJ = 0;
        int endJ = matrix[0].length - 1;//m

        while (fromI <= endI) {

            int indexI = (fromI + endI)/2;
            int currentI = matrix[indexI][0];
            if (currentI == target) {
                return true;
            }

            if (currentI < target) {
                fromI = indexI + 1;
            } else {
                endI = indexI - 1;
                continue;
            }

            while (fromJ <= endJ) {

                int indexJ = (fromJ + endJ)/2;
                int currentJ = matrix[indexI][indexJ];

                if(currentJ == target) {
                    return true;
                }

                if(currentJ < target) {
                    fromJ = indexJ + 1;
                } else {
                    endJ = indexJ - 1;
                }
            }
            fromJ = 0;
            endJ = matrix[0].length - 1;
        }

        return false;
    }
}
