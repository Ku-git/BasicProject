package com.home;

import java.util.Stack;

public class AVLTree_GPT {
    Node root;

    // 獲取節點的高度
    int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // 獲取兩個數字中的最大值
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // 右旋操作
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 執行旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 左旋操作
    Node leftRotate(Node rotateNode) {
        Node anchor = rotateNode.right;
        Node rotatePosition = anchor.left;

        // 執行旋轉
        anchor.left = rotateNode;
        rotateNode.right = rotatePosition;

        // 更新高度
        rotateNode.height = max(height(rotateNode.left), height(rotateNode.right)) + 1;
        anchor.height = max(height(anchor.left), height(anchor.right)) + 1;

        return anchor;
    }

    // 獲取平衡因子
    int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // 插入節點
    void insert(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node current = root;
        Node parent = null;

        // 尋找插入位置
        while (current != null) {
            stack.push(current);
            parent = current;

            if (value < current.value)
                current = current.left;
            else if (value > current.value)
                current = current.right;
            else
                return; // 重複的鍵不被允許
        }

        // 插入新節點
        Node newNode = new Node(value);

        if (value < parent.value)
            parent.left = newNode;
        else
            parent.right = newNode;

        // 更新節點的高度
        newNode.height = 1 + max(height(newNode.left), height(newNode.right));

        // 進行平衡調整
        while (!stack.isEmpty()) {
            Node node = stack.pop();

            // 更新節點的高度
            node.height = 1 + max(height(node.left), height(node.right));

            // 獲取節點的平衡因子
            int balance = getBalance(node);

            // 如果節點不平衡，則進行旋轉操作
            // 左-左情況
            if (balance > 1 && value < node.left.value)
                node = rightRotate(node);

            // 右-右情況
            if (balance < -1 && value > node.right.value)
                node = leftRotate(node);

            // 左-右情況
            if (balance > 1 && value > node.left.value) {
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }

            // 右-左情況
            if (balance < -1 && value < node.right.value) {
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }

            // 更新父節點的子節點
            if (!stack.isEmpty()) {
                Node parentOfNode = stack.peek();

                if (value < parentOfNode.value)
                    parentOfNode.left = node;
                else
                    parentOfNode.right = node;
            } else {
                root = node; // 更新根節點
            }
        }
    }

    // 中序遍歷
    void inorder() {
        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.value + " ");

            current = current.right;
        }
    }

    public static void main(String[] args) {
        AVLTree_GPT tree = new AVLTree_GPT();

        // 插入節點
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        
        tree.insert(25);
        tree.insert(50);

        // 中序遍歷樹
        System.out.println("中序遍歷樹：");
        tree.inorder();
    }
    
    private class Node {
        int value;
        int height;
        Node left, right;

        Node(int value) {
            this.value = value;
            height = 1;
        }
    }
}
