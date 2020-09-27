package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @program: example
 * @description:
 * @author: Zhang Chong
 * @create: 2020/1/16 15:24
 **/
public class Solution {

	public boolean Find(int target, int[][] array) {
		int yLength = array[0].length;
		int xLength = array.length;
		int i = 0;
		int j = yLength - 1;
		while (j >= 0) {
			if (target == array[i][j]) {
				return true;
			} else if (target > array[i][j]) {
				i++;
				if (i == xLength) {
					return false;
				}
			} else {
				j--;
			}
		}
		return false;
	}

	/**
	 * IntStream.range(0,str.length())
	 * .forEach(index->{
	 * char c = str.charAt(index);
	 * if (c == ' '){
	 * sb.append("%20");
	 * }else {
	 * sb.append(c);
	 * }* 				});
	 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
	 *
	 * @param str
	 * @return
	 */
	public String replaceSpace(StringBuffer str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == ' ') {
				sb.append("%20");
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
	 *
	 * @param listNode
	 * @return
	 */
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

		ArrayList<Integer> list = new ArrayList<>();
		while (listNode != null) {
			list.add(listNode.val);
			listNode = listNode.next;
		}
		int size = list.size();
		for (int i = 0; i <= size / 2 - 1; i++) {
			int temp = list.get(i);
			list.set(i, list.get(size - 1 - i));
			list.set(size - 1 - i, temp);
		}
		return list;
	}

	public static class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}


	/**
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
	 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
	 * 则重建二叉树并返回。
	 *
	 * @param pre
	 * @param in
	 * @return
	 */
	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		if (pre.length == 0 || in.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(pre[0]);
		int rootValue = pre[0];

		int index = 0;
		for (int i = 0; i < in.length; i++) {
			if (rootValue == in[i]) {
				index = i;
				break;
			}
		}

		root.left = reConstructBinaryTree(copyOfRange(pre, 1, index + 1), copyOfRange(in, 0, index));
		root.right = reConstructBinaryTree(copyOfRange(pre, index + 1, pre.length), copyOfRange(in, index + 1, in.length));
		return root;
	}

	public int[] copyOfRange(int[] array, int from, int to) {
		int[] result = new int[to - from];
		for (int i = from; i < to; i++) {
			result[i - from] = array[i];
		}
		return result;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
	 */
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();
	int count = 0;

	public void push(int node) {
		stack1.push(node);
	}

	public int pop() {
		/*stack2.push(count);
		count++;
		return stack1.get(stack2.pop());*/
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		int popNode = stack2.pop();
		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
		return popNode;
	}

	/**
	 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
	 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
	 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
	 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
	 *
	 * @param array
	 * @return
	 */
	public int minNumberInRotateArray(int[] array) {
		int index = array.length;
		if (index == 0) {
			return 0;
		}
		int min = 1;
		for (int i = 0; i < index; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}

	/**
	 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
	 * n<=39
	 *
	 * @param n
	 * @return
	 */
	public int Fibonacci(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return Fibonacci(n - 1) + Fibonacci(n - 2);
		}
	}

	public int JumpFloorII(int target) {
		if (target == 0) {
			return 0;
		}
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int i = 1; i <= target; i++) {
			dp[i] = 0;
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j];
			}
		}
		return dp[target];
	}

	/**
	 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
	 *
	 * @param target
	 * @return
	 */
	public int RectCover(int target) {
		if (target <= 2) {
			return target;
		} else {
			return RectCover(target - 1) + RectCover(target - 2);
		}
	}

	/**
	 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
	 *
	 * @param n
	 * @return
	 */
	public int NumberOf1(int n) {

		int count = 0;
		if (n < 0) {
			count++;
			n = -n;
		}
		while (n != 0) {
			count += n % 2;
			n = n / 2;
		}
		return count;
	}

	/**
	 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
	 * <p>
	 * 保证base和exponent不同时为0
	 *
	 * @param base
	 * @param exponent
	 * @return
	 */
	public double Power(double base, int exponent) {
		double result = 1;
		boolean negative = false;
		if (exponent < 0) {
			negative = true;
			exponent = -exponent;
		}
		while (exponent-- > 0) {
			result *= base;
		}
		if (negative) {
			result = 1.0 / result;
		}
		return result;
	}

	/**
	 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
	 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
	 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
	 *
	 * @param array
	 */
	public void reOrderArray(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] % 2 == 0 && array[j + 1] % 2 == 1) {
					int t = array[j];
					array[j] = array[j + 1];
					array[j + 1] = t;
				}
			}
		}
	}

	/**
	 * 输入一个链表，输出该链表中倒数第k个结点。
	 *
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode FindKthToTail(ListNode head, int k) {
		int length = 0;
		ListNode root = head;
		while (root != null) {
			length++;
			root = root.next;
		}

		int k1 = length - k + 1;
		if (k1 <= 0) {
			return null;
		}
		int count = 1;
		root = head;
		while (count++ < k1) {
			root = root.next;
		}
		return root;
	}

	public ListNode ReverseList(ListNode head) {
		ListNode newRoot = new ListNode(0);
		while (head != null) {
			ListNode temp = head;
			head = head.next;

			temp.next = newRoot.next;
			newRoot.next = temp;
		}
		return newRoot.next;
	}

	public ListNode Merge(ListNode list1, ListNode list2) {
		ListNode newRoot = new ListNode(-1);
		ListNode insertNode = newRoot;
		while (list1 != null || list2 != null) {
			ListNode temp;
			if (list1 == null) {
				temp = list2;
				list2 = list2.next;
			} else if (list2 == null) {
				temp = list1;
				list1 = list1.next;
			} else {
				if (list1.val > list2.val) {
					temp = list2;
					list2 = list2.next;
				} else {
					temp = list1;
					list1 = list1.next;
				}
			}

			temp.next = null;

			insertNode.next = temp;
			insertNode = insertNode.next;
		}
		return newRoot.next;
	}

	/**
	 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
	 * {8,8,7,9,2,#,#,#,#,4,7},{8,9,2}
	 * boolean result = false;
	 * //当Tree1和Tree2都不为零的时候，才进行比较。否则直接返回false
	 * if (root2 != null && root1 != null) {
	 * //如果找到了对应Tree2的根节点的点
	 * if(root1.val == root2.val){
	 * //以这个根节点为为起点判断是否包含Tree2
	 * result = doesTree1HaveTree2(root1,root2);
	 * }
	 * //如果找不到，那么就再去root的左儿子当作起点，去判断时候包含Tree2
	 * if (!result) {
	 * result = HasSubtree(root1.left,root2);
	 * }
	 * <p>
	 * //如果还找不到，那么就再去root的右儿子当作起点，去判断时候包含Tree2
	 * if (!result) {
	 * result = HasSubtree(root1.right,root2);
	 * }
	 * }
	 * //返回结果
	 * return result;
	 *
	 * @param root1
	 * @param root2
	 * @return
	 */
	public boolean HasSubtree(TreeNode root1, TreeNode root2) {
		boolean result = false;
		if (root2 != null && root1 != null) {
			if (root1.val == root2.val) {
				result = judgeSubTree(root1, root2);
			}
			if (!result) {
				result = HasSubtree(root1.left, root2);
			}
			if (!result) {
				result = HasSubtree(root1.right, root2);
			}
		}
		return result;
	}

	private boolean judgeSubTree(TreeNode root1, TreeNode root2) {

		if (root2 == null) {
			return true;
		}
		if (root1 == null) {
			return false;
		}
		if (root1.val != root2.val) {
			return false;
		}
		return judgeSubTree(root1.left, root2.left) && judgeSubTree(root1.right, root2.right);
	}


	public static void main(String[] args) {

		int n = 10;
		System.out.println(n + "的二进制中1的个数：" + new Solution().NumberOf1(n));
	}
}