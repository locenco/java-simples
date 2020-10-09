package top.macondo.aigorithm.leetcode;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * //num1,num2分别为长度为1的数组。传出参数
 * //将num1[0],num2[0]设置为返回结果
 * @author: zhangchong
 * @Date: 2020/10/8 11:13
 **/
public class JZ40 {
	public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
		if (array == null) {
			return;
		}
		if (array.length == 2) {
			num1[0] = array[0];
			num2[0] = array[1];
			return;
		}
		int bitResult = 0;
		for (int i = 0; i < array.length; i++) {
			bitResult ^= array[i];
		}
		int index = findFirst1(bitResult);
		for (int i = 0; i < array.length; i++) {
			if (isBit1(array[i], index)) {
				num1[0] ^= array[i];
			} else {
				num2[0] ^= array[i];
			}
		}


	}
	private int findFirst1(int bitResult){
		int index = 0;
		while ((bitResult & 1) == 0 && index < 32) {
			bitResult >>= 1;
			index++;
		}
		return index;
	}

	private boolean isBit1(int target, int index){
		return ((target >> index) & 1) == 1;
	}
}
