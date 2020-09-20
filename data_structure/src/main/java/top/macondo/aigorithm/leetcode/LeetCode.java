package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * zhangchong
 **/
public class LeetCode {
	public static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;

		}
	}

	public void Mirror(TreeNode root) {
		if (root != null) {
			TreeNode temp = root.left;
			root.left = root.right;
			root.right = temp;
			Mirror(root.left);
			Mirror(root.right);
		}
	}

	public ArrayList<Integer> printMatrix(int[][] matrix) {
		int x = matrix[0].length;
		int y = matrix.length;
		int[] borders = {0, y - 1, 0, x - 1};
		int matrixSum = x * y;
		ArrayList<Integer> result = new ArrayList<>();
		printACircle(matrix, borders, result);
		return result;
	}

	public void printACircle(int[][] matrix, int[] borders, ArrayList<Integer> result) {
		int xLeft = borders[2], xRight = borders[3], yTop = borders[0], yBottom = borders[1];
		if (xLeft > xRight || yTop > yBottom) {
			return;
		} else if (xLeft == xRight || yTop == yBottom) {
			for (int i = xLeft; i <= xRight; i++) {
				result.add(matrix[yTop][i]);
			}
			for (int i = yTop + 1; i <= yBottom; i++) {
				result.add(matrix[i][xRight]);
			}
			return;
		} else {
			for (int i = xLeft; i <= xRight; i++) {
				result.add(matrix[yTop][i]);
			}
			for (int i = yTop + 1; i <= yBottom; i++) {
				result.add(matrix[i][xRight]);
			}
			for (int i = xRight - 1; i >= xLeft; i--) {
				result.add(matrix[yBottom][i]);
			}
			for (int i = yBottom - 1; i >= yTop + 1; i--) {
				result.add(matrix[i][xLeft]);
			}
			borders = new int[]{yTop + 1, yBottom - 1, xLeft + 1, xRight - 1};
			printACircle(matrix, borders, result);
		}


	}

	public static void main(String[] args) {
		SolutionStack stack = new SolutionStack();
		stack.push(4);
		stack.push(2);
		stack.push(3);
		stack.push(1);
		stack.pop();
		System.out.println(stack.top());
		System.out.println(stack.min());
	}

	public static class SolutionStack {

		private int[] stack = new int[1024];
		private int top = -1;
		private int[] min = new int[1024];
		private int minTop = -1;

		public void push(int node) {
			stack[++top] = node;
			if (minTop < 0) {
				min[0] = top;
				minTop = 0;
			} else if (stack[min[minTop]] > node) {
				min[++minTop] = top;
			}
		}

		public void pop() {
			if (top - 1 < min[minTop]) {
				minTop--;
			}
			top--;
		}

		public int top() {
			if (top - 1 < min[minTop]) {
				minTop--;
			}
			return stack[top--];
		}

		public int min() {
			return stack[min[minTop]];
		}
	}

	public boolean IsPopOrder(int[] pushA, int[] popA) {
		int length = pushA.length;
		int Apos = 0;
		int Bpos = 0;
		int[] pushATemp = new int[length];
		int ATempPos = -1;
		for (; Bpos < length; Bpos++) {
			if (ATempPos != -1 && pushATemp[ATempPos] == popA[Bpos]) {
				ATempPos--;
				continue;
			}
			boolean flag = false;
			while (Apos < length) {
				if (pushA[Apos] == popA[Bpos]) {
					Apos++;
					flag = true;
					break;
				} else {
					pushATemp[++ATempPos] = pushA[Apos];
					Apos++;
				}
			}
			if (!flag) {
				return false;
			}
		}
		return true;
	}

	public class Solution {
		public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
			ArrayList<Integer> result = new ArrayList<>();
			if (root == null) {
				return result;
			}
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);
			while (!queue.isEmpty()) {
				TreeNode node = queue.poll();
				result.add(node.val);
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			return result;
		}
	}

	public boolean VerifySquenceOfBST(int[] sequence) {
		return VerifySquenceOfBST(sequence, 0, sequence.length - 1);
	}

	public boolean VerifySquenceOfBST(int[] sequence, int start, int end) {
		int length = end - start + 1;
		if (length == 1) {
			return true;
		} else if (length < 1) {
			return false;
		} else {
			int root = sequence[end];
			boolean flag = false;
			int flagIndex = -1;
			for (int i = start; i < end; i++) {
				if (flag) {
					if (sequence[i] < root) {
						return false;
					} else {
						continue;
					}
				} else {
					if (sequence[i] < root) {
						continue;
					} else {
						flag = true;
						flagIndex = i;
					}
				}
			}
			if (flagIndex < 0 || flagIndex == start) {
				return true;
			}
			return VerifySquenceOfBST(sequence, start, flagIndex - 1) &&
					VerifySquenceOfBST(sequence, flagIndex, end - 1);
		}
	}

	ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	ArrayList<Integer> OnePath = new ArrayList<>();

	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
		if (root == null) {
			return result;
		}
		OnePath.add(root.val);
		target -= root.val;
		if (target == 0 && root.left == null && root.right == null) {
			result.add(new ArrayList<Integer>(OnePath));
		}
		FindPath(root.left, target);
		FindPath(root.right, target);
		OnePath.remove(OnePath.size() - 1);
		return result;
	}

	public class RandomListNode {
		int label;
		RandomListNode next = null;
		RandomListNode random = null;

		RandomListNode(int label) {
			this.label = label;
		}
	}

	/**
	 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，
	 * 一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的 head。
	 *
	 * @param pHead
	 * @return
	 */
	public RandomListNode Clone(RandomListNode pHead) {
		if (pHead == null) {
			return null;
		}
		//1.
		RandomListNode cur = pHead;
		while (cur != null) {
			RandomListNode clone = new RandomListNode(cur.label);
			clone.next = cur.next;
			cur.next = clone;
			cur = clone.next;
		}

		//2.
		cur = pHead;
		while (cur != null) {
			RandomListNode clone = cur.next;
			if (cur.random != null) {
				clone.random = cur.random.next;
			}
			cur = clone.next;
		}
		//3.
		cur = pHead;
		RandomListNode pCloneHead = pHead.next;
		while (cur.next != null) {
			RandomListNode next = cur.next;
			cur.next = next.next;
			cur = next;
		}
		return pCloneHead;
	}

	private TreeNode pre = null;
	private TreeNode head = null;

	public TreeNode Convert(TreeNode pRootOfTree) {

		convertTreeNode(pRootOfTree);
		return head;
	}

	public void convertTreeNode(TreeNode cur) {
		if (cur == null) {
			return;
		}
		convertTreeNode(cur.left);
		cur.left = pre;
		if (pre != null) {
			pre.right = cur;
		}
		pre = cur;
		if (head == null) {
			head = cur;
		}
		convertTreeNode(cur.right);
	}
}