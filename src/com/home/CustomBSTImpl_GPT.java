package com.home;

public class CustomBSTImpl_GPT {

	private Node root;

	public CustomBSTImpl_GPT() {
		root = null;
	}

	// O(logN)
	public void insert(int value) {
		Node node = new Node(value);
		if (root == null) {
			root = node;
			return;
		}
		Node current = root;
		while (current != null) {
			if (value < current.value) {
				if (current.left == null) {
					current.left = node;
					break;
				}
				current = current.left;
			} else {
				if (current.right == null) {
					current.right = node;
					break;
				}
				current = current.right;
			}
		}
	}

	// O(logN)
	public boolean findOut(int value) {
		return lookup(value) == null ? false : true;
	}

	// O(logN)
	public Node lookup(int value) {
		Node current = root;
		while (current != null) {
			if (value == current.value) {
				return current;
			}
			if (value < current.value) {
				if (current.left == null) {
					return null;
				}
				current = current.left;
			} else {
				if (current.right == null) {
					return null;
				}
				current = current.right;
			}
		}
		return null;
	}

	public void remove(int value) {
		Node current = root;
		Node parent = null;
		
		while(current != null) {
			if(value == current.value) {
				break;
			}
			parent = current;
			if(value < current.value) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		if(current == null) {
			return;
		}
		//condition 1: remove node is leaf node
		if(current.left == null && current.right == null) {
			if(parent == null) {
				root = null;
			} else if(current == parent.left) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
		//condition 2: remove node has at least one node
		else if(current.left != null && current.right == null) {
			if(parent == null) {
				root = current.left;
			} else if(current == parent.left) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		} else if(current.right != null && current.left == null) {
			if(parent == null) {
				root = current.right;
			} else if(current == parent.left) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		}
		//condition 3: remove node has two nodes
		//choose Predecessor or Successor -> successor
		else {//I choose successor
			Node successorParent = current;
			Node successor = current.right;
			
			//find smallest node in these nodes
			while(successor.left != null) {
				successorParent = successor;
				successor = successor.left;
			}
			current.value = successor.value;
			//represent successor is current.right, position dose not change 
			//and that mean successor dose not have left node
			if(current == successorParent) {
				successorParent.right = successor.right;
			} else {
				//successor parent != current -> 
				//successor has forwarded to left node
				//and parent will point to successor right
				//because successor has move to the current node
				successorParent.left = successor.right;
			}
		}
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
		CustomBSTImpl_GPT tree = new CustomBSTImpl_GPT();
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
