package top.macondo.aigorithm.other;

import java.util.Scanner;

public class UnaryPolynomial {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int[][] A1 = scannerLine(in);
		int[][] A2 = scannerLine(in);

		int[][] A3 = UnaryPolynomialMult(A1, A2);
		printLine(A3);

		int[][] sum = UnaryPolynomialAdd(A1, A2);
		printLine(sum);


	}

	/**
	 * 读取一行输入，每行分别先给出多项式非零项的个数，再以指数递降方式输入一个多项式非零项系数和指数
	 *
	 * @return 返回多项式二维数组
	 */
	private static int[][] scannerLine(Scanner in) {
		int number = in.nextInt();
		int[][] A = new int[2][number];
		for (int i = 0; i < number; i++) {
			A[0][i] = in.nextInt();
			A[1][i] = in.nextInt();
		}
		return A;
	}

	/**
	 * 输出分2行，分别以指数递降方式输出乘积多项式以及和多项式非零项的系数和指数。
	 * 数字间以空格分隔，但结尾不能有多余空格。零多项式应输出0 0
	 *
	 * @param A
	 */
	private static void printLine(int[][] A) {
		StringBuffer output = new StringBuffer();

		int number = A[0].length;
		for (int i = 0; i < number; i++) {
			if (A[0][i] == 0) {
				continue;
			}
			output.append(A[0][i]);
			output.append(" ");
			output.append(A[1][i]);
			output.append(" ");
		}
		if (output.toString().length() == 0) {
			System.out.println("0 0");
		} else {
			System.out.println(output.toString().substring(0, output.toString().length() - 1));
		}
	}

	private static int[][] UnaryPolynomialAdd(int[][] A1, int[][] A2) {
		int[][] resultAdd = new int[2][A1[0].length + A2[0].length];
		int pos1 = 0;
		int pos2 = 0;
		int pos = 0;
		while (pos1 < A1[0].length || pos2 < A2[0].length) {
			if (pos1 == A1[0].length) {
				resultAdd[0][pos] = A2[0][pos2];
				resultAdd[1][pos] = A2[1][pos2];
				pos++;
				pos2++;
			} else if (pos2 == A2[0].length || A1[1][pos1] > A2[1][pos2]) {
				resultAdd[0][pos] = A1[0][pos1];
				resultAdd[1][pos] = A1[1][pos1];
				pos++;
				pos1++;
			} else if (A1[1][pos1] < A2[1][pos2]) {
				resultAdd[0][pos] = A2[0][pos2];
				resultAdd[1][pos] = A2[1][pos2];
				pos++;
				pos2++;
			} else {
				resultAdd[0][pos] = A1[0][pos1] + A2[0][pos2];
				resultAdd[1][pos] = A1[1][pos1];
				pos++;
				pos1++;
				pos2++;
			}


		}

		return resultAdd;
	}

	private static int[][] UnaryPolynomialMult(int[][] A1, int[][] A2) {
		int[][] resultMult = new int[2][A1[0].length * A2[0].length];
		int pos = 0;
		for (int i = 0; i < A1[0].length; i++) {
			for (int j = 0; j < A2[0].length; j++) {
				resultMult[0][pos] = A1[0][i] * A2[0][j];
				resultMult[1][pos] = A1[1][i] + A2[1][j];
				pos++;
			}
		}
		for (int i = 0; i < resultMult[0].length; i++) {
			for (int j = i + 1; j < resultMult[0].length; j++) {
				if (resultMult[1][i] < resultMult[1][j]) {
					int a = resultMult[0][i];
					int x = resultMult[1][i];
					resultMult[0][i] = resultMult[0][j];
					resultMult[1][i] = resultMult[1][j];
					resultMult[0][j] = a;
					resultMult[1][j] = x;
				} else if (resultMult[1][i] == resultMult[1][j]) {
					resultMult[0][i] += resultMult[0][j];
					resultMult[0][j] = 0;
					resultMult[1][j] = 0;
				}
			}
		}
		return resultMult;
	}
}
