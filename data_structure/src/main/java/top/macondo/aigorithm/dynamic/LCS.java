package top.macondo.aigorithm.dynamic;

/**
 * 最长公共子序列
 * @author: zhangchong
 * @Date: 2020/10/12 10:35
 **/
public class LCS {
	public static void main(String[] args) {
		LCS lCS = new LCS();
		String X = "ABCBDAB";
		String Y = "BDCABA";
		int[][] c = new int[X.length() + 1][Y.length() + 1];
		String[][] b = new String[X.length() + 1][Y.length() + 1];
		lCS.LCSLength(X, Y, c, b);
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				System.out.print(b[i][j] + c[i][j] +" ");
			}
			System.out.println();
		}
		lCS.printLCS(b, X, X.length(), Y.length());
	}

	public void printLCS(String[][] b, String X, int i, int j) {
		if (i == 0 || j == 0) {
			return;
		}
		if("↖".equals(b[i][j])){
			printLCS(b, X, i - 1, j - 1);
			System.out.print(X.charAt(i-1));
		} else if ("←".equals(b[i][j])) {
			printLCS(b, X, i, j - 1);
		}else {
			printLCS(b, X, i - 1, j);
		}
	}

	/**
	 * 构造结果
	 * @param X
	 * @param Y
	 * @return
	 */
	public void LCSLength(String X, String Y,int[][] c, String[][] b) {
		int m = X.length();
		int n = Y.length();

		char[] x = X.toCharArray();
		char[] y = Y.toCharArray();

		for (int i = 0; i <= m; i++) {
			c[i][0] = 0;
		}
		for (int i = 0; i <= n; i++) {
			c[0][i] = 0;
		}

		for (int i = 0; i < m; i++) {
		    for (int j = 0; j < n; j++) {
				if (x[i] == y[j]) {
					c[i + 1][j + 1] = c[i][j] + 1;
					b[i + 1][j + 1] = "↖";
				} else if (c[i][j + 1] >= c[i + 1][j]) {
					c[i + 1][j + 1] = c[i][j + 1];
					b[i + 1][j + 1] = "↑";
				} else {
					c[i + 1][j + 1] =c[i + 1][j];
					b[i + 1][j + 1] = "←";
				}
		    }
		}
	}
}
