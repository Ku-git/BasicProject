package com.home;

public class CustomBSTImpl_ownMethod {

	private Node root;
	
	public CustomBSTImpl_ownMethod() {
		root = null;
	}
	//O(logN)
	public void insert(int value) {
		Node node = new Node(value);
		if(root == null) {
			root = node;
			return;
		}
		Node current = root;
		while(current != null) {
			if(value < current.value) {
				if(current.left == null) {
					current.left = node;
					break;
				}
				current = current.left;
			} else {
				if(current.right == null) {
					current.right = node;
					break;
				}
				current = current.right;
			}
		}
	}
	
	//O(logN)
	public boolean findOut(int value) {
		return lookup(value) == null? false: true;
	}
	
	//O(logN)
	public Node lookup(int value) {
		Node current = root;
		while(current != null) {
			if(value == current.value) {
				return current;
			}
			if(value < current.value) {
				if(current.left == null) {
					return null;
				}
				current = current.left;
			} else {
				if(current.right == null) {
					return null;
				}
				current = current.right;
			}
		}
		return null;
	}
	
	//O(logN) -> logN + 2 logM + 1(or 2)
	/** 此方法可行,但是執行判斷方式不符合BST的規則 **/
	public void remove(int value) {
		Node removeNode = null;
		Node current = root;
		Node parent = root;
		boolean left = false;
		boolean right = false;
		//O(logN)
		while(current != null) {
			if(value == current.value) {
				removeNode = current;
				break;
			}
			if(value < current.value) {
				if(current.left == null) {
					break;
				}
				parent = current;
				current = current.left;
				left = true;
				right = false;
			} else {
				if(current.right == null) {
					break;
				}
				parent = current;
				current = current.right;
				left = false;
				right = true;
			}
		}
		if(removeNode == null) {
			return;
		}
		if(removeNode.left == null && removeNode.right == null) {
			if(parent == root) {
				root = null;
				return;
			}
			//O(1)
			removeLeaf(parent, removeNode);
			return;
		}
		Node successor = null;
		if(removeNode.right != null) {
			successor = findSuccessor(removeNode);// < O(logN) = O(logM)
			int changeVal = successor.value;
			removeSuccessor(removeNode, successor);// < O(logN) = O(logM)
			removeNode.value = changeVal;
		} else if(removeNode.left != null) {
			if(parent == root) {
				root = removeNode.left;
				return;
			}
			//O(1)
			successor = removeNode.left;
			if(right) {
				parent.right = successor;
			}
			if(left) {
				parent.left = successor;
			}
		}
	}
	
	//O(1)
	private void removeLeaf(Node parent, Node removeNode) {
		if(parent.left == removeNode) {
			parent.left = null;
		} else if(parent.right == removeNode){
			parent.right = null;
		}
	}
	
	private void removeSuccessor(Node removeNode, Node succcessor) {
		Node parent = removeNode;
		Node current = removeNode.right;
		if(current == succcessor) {
			if(succcessor.right != null) {
				parent.right = succcessor.right;
			} else {
				parent.right = null;
			}
			return;
		}
		while(current != null) {
			if(current.left == succcessor) {
				if(succcessor.right != null) {
					current.left = succcessor.right;
				} else {
					current.left = null;
				}
				break;
			} else if(current.right == succcessor) {
				if(succcessor.right != null) {
					current.right = succcessor.right;
				} else {
					current.right = null;
				}
				break;
			}
			if(current.left != null) {
				current = current.left;
			} else if(current.right != null){
				current = current.right;
			}
		}
	}
	
	//O(logN) -> log (N - node steps) + logN
	private Node findSuccessor(Node node) {
		Node successor = null;
		if(node.right != null) {
			successor = node.right;
			while(successor != null) {
				if(successor.left != null) {
					successor = successor.left;
				} else if(successor.right != null && successor.value > successor.right.value){
					successor = successor.right;
				} else if(successor.left == null && (successor.right == null || successor.value < successor.right.value)){
					return successor;
				}
			}
		}
		return successor;
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

        System.out.println(root.value);

        printBST(root.left, indent, false);
        printBST(root.right, indent, true);
    }
	
	public static void main(String[] args) {
		CustomBSTImpl_ownMethod tree = new CustomBSTImpl_ownMethod();
		tree.insert(9);
		tree.insert(1);
		tree.insert(4);
		tree.insert(3);
		tree.insert(5);
		tree.insert(20);
		tree.insert(170);
		tree.insert(30);
		tree.insert(40);
		tree.insert(15);
		// System.out.println(tree);
		printBST(tree.root, "", true);

		System.out.println(tree.findOut(0));
		System.out.println(tree.findOut(170));
		System.out.println(tree.findOut(6));
		System.out.println(tree.findOut(3));
		System.out.println(tree.findOut(9));
		System.out.println(tree.findOut(20));
		System.out.println(tree.findOut(1));
		// tree.remove(1);
		tree.remove(9);
		tree.remove(4);
		tree.insert(9);
		tree.insert(25);
		tree.remove(15);
		tree.remove(170);
		tree.insert(7);
		tree.insert(6);
		tree.insert(8);
		tree.remove(5);
		tree.remove(6);
		tree.remove(9);
		tree.insert(10);
		tree.insert(15);
		tree.insert(12);
		tree.remove(10);
		
		printBST(tree.root, "", true);
	}
	
	private class Node {
		private int value;
		private Node left;
		private Node right;
		
		public Node(int value) {
			this.value = value;
			left = null;
			right = null;
		}
		
	}
	
}
