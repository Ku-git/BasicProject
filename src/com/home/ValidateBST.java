package com.home;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ValidateBST {

	public static boolean validateBST(TreeNode treeNode) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(treeNode);
		TreeNode current = null;
		TreeNode compareNode = null;
		while(!queue.isEmpty()) {
			current = queue.poll();
			if(current.left != null) {
				if(current.left.val >= current.val) {
					return false;
				}
				if(current.left.right != null) {
					compareNode = current.left.right;
					while(compareNode != null){
						if(compareNode.val >=  current.val) {
							return false;
						}
						compareNode = compareNode.right;
					}
				}
				queue.offer(current.left);
			}
			if(current.right != null) {
				if(current.right.val <= current.val) {
					return false;
				}
				if(current.right.left != null) {
					compareNode = current.right.left;
					while (compareNode != null) {
						if(compareNode.val <= current.val) {
							return false;
						}
						compareNode = compareNode.left;
					}
				}
				queue.offer(current.right);
			}
		}
		return true;
	}
	
	/** 
	 * best solution 
	 * time complexity O(n)
	 * space complexity O(n)
	 **/
	public static boolean isValidateBST(TreeNode root) {
		return isValidateBST(root, null, null);
	}
	
	/**
	 * 
	 */
	private static boolean isValidateBST(TreeNode node, Integer min, Integer max) {
		if(node == null) {//base case #1
			return true;
		}
		if((min != null && node.val <= min) || (max != null && node.val >= max)) {//base case #2
			return false;
		}
		//recursive case
		return isValidateBST(node.left, min, node.val) && isValidateBST(node.right, node.val, max);
	}
	
	public static List<Integer> printBST(TreeNode treeNode) {
		List<Integer> result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(treeNode);
		TreeNode current = null;
		while(!queue.isEmpty()) {
			current = queue.poll();
			result.add(current.val);
			if(current.left != null) {
				queue.offer(current.left);
			}
			if(current.right != null) {
				queue.offer(current.right);
			}
		}
		return result;
	}


	public static void main(String[] args) {
		TreeNode node = new TreeNode(1);
		TreeNode node2 = new TreeNode(3);
		TreeNode node3 = new TreeNode(5);
//		TreeNode node01 = new TreeNode(0);
//		TreeNode node02 = new TreeNode(2);
//		node.left = node01;
//		node.right = node02;
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(6);
		node2.left = node;
		node2.right = node3;
		node3.left = node4;
		node3.right = node5;
		System.out.println(isValidateBST(node2));
		System.out.println(printBST(node2));
		System.out.println(validateBST(node2));
	}

	
}


	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	
		TreeNode() {
		}
	
		TreeNode(int val) {
			this.val = val;
		}
	
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
