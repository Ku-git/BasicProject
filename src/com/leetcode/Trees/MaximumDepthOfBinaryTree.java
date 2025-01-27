package com.leetcode.Trees;

public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {

        MaximumDepthOfBinaryTree method = new MaximumDepthOfBinaryTree();
        TreeNode root = new TreeNode(4);
        TreeNode l1 = new TreeNode(2);
        TreeNode r1 = new TreeNode(7);
        root.left = l1;
        root.right = r1;

        TreeNode l2 = new TreeNode(1);
        TreeNode r2 = new TreeNode(3);
        l1.left = l2;
        l1.right = r2;

        TreeNode l3 = new TreeNode(6);
        TreeNode r3 = new TreeNode(9);
        r1.left = l3;
        r1.right = r3;

        r2.right = new TreeNode(2);

        int result = method.maxDepth(root);
        System.out.println(result);
    }

    public int maxDepth(TreeNode root) {

        if(root == null) {
            return 0;
        }

        int depth = searchDepth(root, 1);

        return depth;
    }

    private int searchDepth(TreeNode node, int depth) {

        if(node.left == null && node.right == null) {
            return depth;
        }

        int leftDepth = 0;
        if(node.left != null) {
            leftDepth = searchDepth(node.left, depth + 1);
        }

        int rightDepth = 0;
        if(node.right != null) {
            rightDepth = searchDepth(node.right, depth + 1);
        }

        return Math.max(leftDepth, rightDepth);
    }
}
