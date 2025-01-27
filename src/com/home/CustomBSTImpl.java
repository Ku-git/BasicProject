package com.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CustomBSTImpl {

	private Node root;
	
	public CustomBSTImpl() {
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
	
	public boolean remove(int value) {
		if(root == null) {
			return false;
		}
		Node current = root;
		Node parent = null;
		while(current != null) {
			if(value < current.value) {
				parent = current;
				current = current.left;
			} else if(value > current.value) {
				parent = current;
				current = current.right;
			} else if(value == current.value) {
				
				//option 1: no-right child
				if(current.right == null) {
					if(parent == null) {
						root = current.left;
						return true;
					} else {
						if(current.value < parent.value) {
							parent.left = current.left;
						} else if(current.value > parent.value) {
							parent.right = current.left;
						}
					}
					return true;
				} else if(current.right.left == null) {//option 2: right child does not have left child
					current.right.left = current.left;
					if(parent == null) {
						root = current.right;
						return true;
					} else {
						if(current.value < parent.value) {
							parent.left = current.right;
						} else if(current.value > parent.value) {
							parent.right = current.right;
						}
					}
					return true;
				} else {//option 3: right child has left child
					Node leftMost = current.right.left;
					Node leftMostParent = current.right;
					while(leftMost.left != null) {
						leftMostParent = leftMost;
						leftMost = leftMost.left;
					}
					leftMostParent.left = leftMost.right;
					leftMost.left = current.left;
					leftMost.right = current.right;
					if(parent == null) {
						root = leftMost;
						return true;
					} else {
						if(current.value < parent.value) {
							parent.left = leftMost;
						} else if (current.value > parent.value) {
							parent.right = leftMost;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 1. identify base case
	 * 2. identify recursive case
	 * 3. get closer and closer then return
	 */
	public Node traverseWithRecursive(Node node) {
	    if (node == null) {
	        return null;
	    }
	    Node tree = new Node(node.value);
	    tree.left = node.left == null? null: traverseWithRecursive(node.left);
	    tree.right = node.right == null? null: traverseWithRecursive(node.right);
	    return tree;
	}
	
	public String printWithIterative(Node node) {
		if(node == null){
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("└─" + node.value);
		builder.append("\n");
		String prefix = "   ";
		Node before = null;
		Node current = node;
		while(current != null) {
			if(current.left != null) {
				before = current;
				current = current.left;
				current.parent = before;
				builder.append(prefix);
				builder.append("├─" + current.value);
				builder.append("\n");
				prefix += "│  ";
			} else if(current.right != null) {
				before = current;
				current = current.right;
				current.parent = before;
				builder.append(prefix);
				builder.append("└─" + current.value);
				builder.append("\n");
				prefix += "   ";
			} else {
				current = current.parent;
				if(current == null)
					break;
				if(current.left != null) {
					current.left = null;
				} else if(current.right != null){
					current.right = null;
				}
				prefix = prefix.substring(0, prefix.length() - 3);
			}
		}
		return builder.toString();
	}
	
	public static String printTree(Node tree, String prefix, boolean isLeft) {
	    StringBuilder sb = new StringBuilder();
	    if (tree == null) {
	        return sb.toString();
	    }

	    sb.append(prefix);
	    sb.append(isLeft ? "├─" : "└─");
	    sb.append(tree.value);
	    sb.append("\n");

	    String childPrefix = prefix + (isLeft ? "│  " : "   ");
	    sb.append(printTree(tree.left, childPrefix, true));
	    sb.append(printTree(tree.right, childPrefix, false));

	    return sb.toString();
	}
	
	public static String printTree(Node tree) {
	    StringBuilder sb = new StringBuilder();
	    if (tree == null) {
	        return sb.toString();
	    }

	    Deque<Node> stack = new LinkedList<>();
	    Map<Node, String> nodePrefixes = new HashMap<>();
	    stack.push(tree);
	    nodePrefixes.put(tree, "");

	    while (!stack.isEmpty()) {
	        Node node = stack.pop();
	        String prefix = nodePrefixes.get(node);
	        boolean isLast = stack.isEmpty();

	        sb.append(prefix);
	        sb.append(isLast ? "└─" : "├─");
	        sb.append(node.value);
	        sb.append("\n");

	        if (node.right != null) {
	            String childPrefix = prefix + (isLast ? "   " : "│  ");
	            stack.push(node.right);
	            nodePrefixes.put(node.right, childPrefix);
	        }

	        if (node.left != null) {
	            String childPrefix = prefix + (isLast ? "   " : "│  ");
	            stack.push(node.left);
	            nodePrefixes.put(node.left, childPrefix);
	        }
	    }

	    return sb.toString();
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
	
	public List<Integer> breadthFirstSearch() {
		Node currentNode = this.root;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(currentNode);
		List<Integer> result = new ArrayList<>();
		while(!queue.isEmpty()) {
			currentNode = queue.poll();
			result.add(currentNode.value);
			if(currentNode.left != null) {
				queue.offer(currentNode.left);
			}
			if(currentNode.right != null) {
				queue.offer(currentNode.right);
			}
		}
		return result;
	}
	
	/**
	 * 1. identify base case
	 * 2. identify recursive case
	 * 3. get closer and closer then return
	 */
	public List<Integer> breadthFirstSearchRecursive(Queue<Node> queue, List<Integer> result) {
		if(queue.isEmpty()) {
			return result;
		}
		Node current = queue.poll();
		result.add(current.value);
		if(current.left != null) {
			queue.offer(current.left);
		}
		if(current.right != null) {
			queue.offer(current.right);
		}
		return breadthFirstSearchRecursive(queue, result);
	}
	
	public List<Integer> DFSInOrder() {
		return inorderRecursive(root, new ArrayList<Integer>());
	}
	
	private List<Integer> inorderRecursive(Node node, List<Integer> result) {
		if(node.left != null) {
			inorderRecursive(node.left, result);
		}
		result.add(node.value);
		if(node.right != null) {
			inorderRecursive(node.right, result);
		}
		return result;
	}
	
	public List<Integer> DFSPreOrder() {
		return preOrderRecursive(root, new ArrayList<Integer>());
	}
	
	private List<Integer> preOrderRecursive(Node node, List<Integer> result) {
		result.add(node.value);
		if(node.left != null) {
			preOrderRecursive(node.left, result);
		}
		if(node.right != null) {
			preOrderRecursive(node.right, result);
		}
		return result;
	}

	public List<Integer> DFSPostOrder() {
		return postOrderRecursive(root, new ArrayList<Integer>());
	}
	
	private List<Integer> postOrderRecursive(Node node, List<Integer> result){
		if(node.left != null) {
			postOrderRecursive(node.left, result);
		}
		if(node.right != null) {
			postOrderRecursive(node.right, result);
		}
		result.add(node.value);
		return result;
	}
	
	
	
	public static void main(String[] args) {
		CustomBSTImpl tree = new CustomBSTImpl();
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
//		System.out.println(tree);
		printBST(tree.root, "", true);
		
//		System.out.println(tree.findOut(0));
//		System.out.println(tree.findOut(170));
//		System.out.println(tree.findOut(6));
//		System.out.println(tree.findOut(3));
//		System.out.println(tree.findOut(9));
//		System.out.println(tree.findOut(20));
//		System.out.println(tree.findOut(1));
//		tree.remove(1);
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
		tree.insert(0);
		tree.insert(-1);
		tree.insert(-2);
		
		tree.remove(0);
		tree.insert(0);
		tree.insert(-3);
		tree.remove(-1);
//		printBST(tree.root, "", true);
		
		
		CustomBSTImpl newTree = new CustomBSTImpl();
		newTree.insert(4);
		newTree.insert(2);
		newTree.insert(7);
		newTree.insert(1);
		newTree.insert(3);
		newTree.insert(6);
		newTree.insert(8);
//		System.out.println(tree.breadthFirstSearch());
		
//		List<Integer> recursivePrint = new ArrayList<Integer>();
//		Queue<Node> queue = new LinkedList<CustomBSTImpl.Node>();
//		queue.offer(tree.root);
//		
//		System.out.println(tree.breadthFirstSearchRecursive(queue, recursivePrint));
		System.out.println(tree.DFSInOrder());
		System.out.println(tree.DFSPreOrder());
		System.out.println(tree.DFSPostOrder());
		
		String result = printTree(tree.root, "", false);
		System.out.println(result);
//		Node traverseNode = tree.traverseWithRecursive(tree.root);
//		System.out.println(traverseNode);
//		String result2 = printTree(tree.root);
//		System.out.println(result2);
		String iterative = tree.printWithIterative(tree.root);
		System.out.println(iterative);
		
		
	}
	
	private class Node {
		private int value;
		private Node parent;
		private Node left;
		private Node right;
		
		public Node(int value) {
			this.value = value;
			left = null;
			right = null;
			parent = null;
		}
		
	}
	
}
