package top.macondo.aigorithm.newcoder;

/**
 * @author: zhangchong
 * @Date: 2020/10/18 10:22
 **/
public class NC512 {
	public static void main(String[] args) {
		NC512 nc512 = new NC512();
		System.out.println(nc512.cakeNumber(2));
	}

	public int cakeNumber(int num) {
		int result = 1;
		for (int i = num; i > 1; i--) {
			result = (int) Math.floor((result + 1) * 1.5);
		}
		return result;
	}

}