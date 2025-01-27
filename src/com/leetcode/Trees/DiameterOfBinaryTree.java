package com.leetcode.Trees;

public class DiameterOfBinaryTree {

    public static void main(String[] args) {

        DiameterOfBinaryTree method = new DiameterOfBinaryTree();
        TreeNode root = new TreeNode(4);
        TreeNode l1 = new TreeNode(2);
        TreeNode r1 = new TreeNode(7);
        root.left = l1;
        root.right = r1;

        TreeNode l2 = new TreeNode(1);
        TreeNode r2 = new TreeNode(3);
        l1.left = l2;
        l1.right = r2;

//        TreeNode l3 = new TreeNode(6);
//        TreeNode r3 = new TreeNode(9);
//        r1.left = l3;
//        r1.right = r3;

        l2.right = new TreeNode(2);
        l2.right.right = new TreeNode(3);

        r2.right = new TreeNode(4);
    }



}
