package top.macondo.aigorithm.leetcode;

/**
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
 * 对于A长度为1的情况，B无意义，故而无法构建，因此该情况不会存在。
 * @author: zhangchong
 * @Date: 2020/10/8 16:26
 **/
public class JZ51 {
	public int[] multiply(int[] A) {
		int[] B = new int[A.length];
		if (A.length != 0) {
			B[0] = 1;
		}
		for (int i = 1; i < A.length; i++) {
			B[i] = B[i-1] * A[i - 1];
		}
		int temp = 1;
		for (int i = A.length - 2; i >= 0; i--) {
			temp *= A[i + 1];
			B[i] *= temp;
		}
		return B;
	}
}
