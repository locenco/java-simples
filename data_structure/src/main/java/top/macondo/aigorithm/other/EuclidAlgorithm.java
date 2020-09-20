package top.macondo.aigorithm.other;


/**
 * zhangchong
 * 2020/2/20 15:10
 **/
public class EuclidAlgorithm {

	public int euclidAlgorithm(int a, int b) {
		if (a == 0) {
			return b;
		}
		if (b == 0) {
			return a;
		}
		if (a > b) {
			return euclidAlgorithm(b, a % b);
		} else {
			return euclidAlgorithm(a, b % a);
		}
	}

	public void test() {
		System.out.println(euclidAlgorithm(210, 715));
		System.out.println(euclidAlgorithm(2009, 1394));
	}


}
