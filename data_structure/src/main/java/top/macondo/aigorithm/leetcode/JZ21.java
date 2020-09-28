package top.macondo.aigorithm.leetcode;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）

 * @author: zhangchong
 * @Date: 2020/9/27 13:52
 **/
public class JZ21 {
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
}
