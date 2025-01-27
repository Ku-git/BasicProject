package com.home;


public class BRTree {
    private Node root;
    private Node nil;//sentinel node

    public BRTree() {
        nil = new Node(0);
        nil.color = Color.BLACK;
        root = nil;
    }

    private void leftRotate(Node rotateNode) {
        Node anchor = rotateNode.right;
        rotateNode.right = anchor.left;

        if (anchor.left != nil) {
            anchor.left.parent = rotateNode;
        }

        anchor.parent = rotateNode.parent;

        if (rotateNode.parent == nil) {
            root = anchor;
        } else if (rotateNode == rotateNode.parent.left) {//check rotate node is left side of parent
            rotateNode.parent.left = anchor;
        } else {//rotate node is right side if parent
            rotateNode.parent.right = anchor;
        }

        anchor.left = rotateNode;
        rotateNode.parent = anchor;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;

        if (y.right != nil) {
            y.right.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == nil) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }

        y.right = x;
        x.parent = y;
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        Node parent = nil;
        Node current = root;

        while (current != nil) {
            parent = current;
            if (newNode.value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newNode.parent = parent;

        if (parent == nil) {
            root = newNode;
        } else if (newNode.value < parent.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        newNode.left = nil;
        newNode.right = nil;
        newNode.color = Color.RED;

        insertFixup(newNode);
    }

    private void insertFixup(Node node) {
        while (node.parent.color == Color.RED) {
            if (node.parent == node.parent.parent.left) {//判斷新節點的父節點是否為祖父節點的左側 → 判斷是否為左子樹
                Node y = node.parent.parent.right;//父節點當前對立的右節點
                if (y.color == Color.RED) {//符合red-black原則(對稱),將節點顏色轉換
                    node.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {//顏色不對稱
                	//若符合則為左-右
                    if (node == node.parent.right) {//check node is bigger than parent(right node)
                        node = node.parent;//parent as anchor to left rotate
                        leftRotate(node);
                    }
                    //that is mean node is smaller than parent(left node)
                    //若上述不符,為左-左
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    rightRotate(node.parent.parent);
                }
            } else {//新節點的父節點為祖父節點的右側 → 判斷為右子樹
                Node y = node.parent.parent.left;//父節點當前對立的左節點
                if (y.color == Color.RED) {//符合red-black原則(對稱),將節點顏色轉換
                    node.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {//對應點非紅色 為一黑一紅
                    if (node == node.parent.left) {//判斷新節點是否為父節點的左側 如符合為右-左條件
                        node = node.parent;
                        rightRotate(node);
                    }
                    //若上述不符,則為右-右
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    leftRotate(node.parent.parent);
                }
            }
        }

        root.color = Color.BLACK;
    }
    
    public static void main(String[] args) {
    	BRTree tree = new BRTree();
    	tree.insert(10);
    	tree.insert(20);
    	tree.insert(30);
    	tree.insert(40);
    	tree.insert(50);
    	tree.insert(25);
    	tree.insert(8);
		tree.insert(3);
		tree.insert(21);
		tree.insert(16);
		tree.insert(27);
		tree.insert(9);
		tree.insert(18);
		tree.insert(29);
		tree.insert(10);
//		tree.insert(15);
    	
    	printBST(tree.root, "", true);
    }
    
    public static void printBST(Node root, String indent, boolean isLast) {
        if (root == null) {
            return;
        }

        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println("[" + root.value + "," + root.color + "]");

        printBST(root.left, indent, false);
        printBST(root.right, indent, true);
    }
    
    enum Color {
        RED,
        BLACK
    }

    class Node {
        int value;
        Color color;
        Node left, right, parent;

        Node(int value) {
            this.value = value;
            color = Color.RED;
        }
    }
}
