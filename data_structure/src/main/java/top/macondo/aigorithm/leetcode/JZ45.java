package top.macondo.aigorithm.leetcode;

/**
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
 * @author: zhangchong
 * @Date: 2020/10/8 15:38
 **/
public class JZ45 {
	public static void main(String[] args) {
		JZ45 jz45 = new JZ45();
		System.out.println(jz45.isContinuous(new int[]{1,2,3,4,0,6}));
	}
	public boolean isContinuous(int [] numbers) {
		int n = numbers.length;
		if (  numbers.length == 0) {
			return false;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < n - i; j++) {
				if (numbers[j] > numbers[j + 1]) {
					swap(numbers, j, j + 1);
				}
			}
		}
		int kingCount = 0;
		for (int i = 0; i < n - 1; i++) {
			if (numbers[i] == 0) {
				kingCount++;
				continue;
			}
			int temp =numbers[i + 1] - numbers[i];
			if ( temp > 1){
				kingCount = kingCount - temp + 1;
			}
			if (kingCount < 0 || temp < 1) {
				return false;
			}
		}
		return kingCount >= 0;
	}

	private static void swap(int n[], int i, int j) {
		int temp = n[i];
		n[i] = n[j];
		n[j] = temp;
	}
}
