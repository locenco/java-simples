package top.macondo.aigorithm.leetcode;

/**
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 *
 */
public class JZ33 {
	public static void main(String[] args) {
		System.out.println(new JZ33().GetUglyNumber_Solution(10));
	}
	final int d[] = { 2, 3, 5 };
	public int GetUglyNumber_Solution(int index) {
		if(index == 0) return 0;
		int a[] = new int[index];
		a[0] = 1;
		//表示最小用于乘2比较数在数组a中的【位置】
		int p[] = new int[] { 0, 0, 0 };
		int num[] = new int[] { 2, 3, 5 };
		int cur = 1;

		while (cur < index) {
			//1. num 中最小值的索引
			int m = finMin(num[0], num[1], num[2]);
			//2. 写入最小值到a中
			if (a[cur - 1] < num[m])
				a[cur++] = num[m];
			//3.标记数组，p[m]对应a中的索引，分别乘d[m]
			p[m] += 1;
			num[m] = a[p[m]] * d[m];
		}
		return a[index - 1];
	}

	private int finMin(int num2, int num3, int num5) {
		int min = Math.min(num2, Math.min(num3, num5));
		return min == num2 ? 0 : min == num3 ? 1 : 2;
	}
}
