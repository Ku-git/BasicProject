package com.leetcode.Trees;

public class InvertBinaryTree {

    public static void main(String[] args) {

        InvertBinaryTree method = new InvertBinaryTree();
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

        TreeNode node = method.invertTree(root);
        System.out.println(node.val);
    }

    public TreeNode invertTree(TreeNode root) {

        if(root == null) {
            return null;
        }

        invert(root);
        return root;
    }

    public void invert(TreeNode root) {

        if(root.left == null && root.right == null) {
            return;
        }

        TreeNode left = null;
        TreeNode right = null;
        if(root.left != null) {
            left = root.left;
            invert(left);
        }
        if(root.right != null) {
            right = root.right;;
            invert(right);
        }
        root.left = right;
        root.right = left;
    }
}
