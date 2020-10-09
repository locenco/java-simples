package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;

/**
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 * @author: zhangchong
 * @Date: 2020/10/9 17:13
 **/
public class JZ41 {
	public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		for (int i = (int) Math.sqrt(2 * sum); i >= 2; i--) {
			//判断奇数和偶数
			if ((i % 2 == 1 && sum % i == 0) || (sum % i) * 2 == i) {
				ArrayList<Integer> temp = new ArrayList<>(i);
				//序列开头
				int k = (sum / i) - (i - 1) / 2;
				for (int j = 0; j < i; j++) {
					temp.add(k++);
				}
				result.add(temp);
			}
		}
		return result;
	}
}
