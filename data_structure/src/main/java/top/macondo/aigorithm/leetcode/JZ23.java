package top.macondo.aigorithm.leetcode;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则返回true,否则返回false。假设输入的数组的任意两个数字都互不相同。
 *
 * @author: zhangchong
 * @Date: 2020/9/27 14:22
 **/
public class JZ23 {
	public boolean VerifySquenceOfBST(int[] sequence) {
		return VerifySquenceOfBST(sequence, 0, sequence.length - 1);
	}

	/**
	 * 数组末尾是根，从起始到末尾，左边小于根，右边大于根。不满足返回false
	 * 满足则从左边右边部分的边界分成两部分递归
	 * @param sequence
	 * @param start
	 * @param end
	 * @return
	 */
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
}
