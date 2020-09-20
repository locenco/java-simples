package top.macondo.aigorithm.other;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int a[] = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = in.nextInt();
		}

		int thisSum = 0, maxSum = 0;
		int startIndex = 0, endIndex = 0;
		boolean postiveSign = false;
		for (int i = 0; i < N; i++) {
			thisSum += a[i];
			if (thisSum > maxSum) {
				maxSum = thisSum;
				endIndex = i;
			}
			if (thisSum < 0) {
				thisSum = 0;
			}
			if (a[i] >= 0) {
				postiveSign = true;
			}
		}
		thisSum = 0;
		for (int i = endIndex; i >= 0; i--) {
			thisSum = thisSum + a[i];
			if (thisSum == maxSum) {
				startIndex = i;
			}
		}
		if (maxSum > 0) {
			System.out.printf("%d %d %d", maxSum, a[startIndex], a[endIndex]);
		} else if (postiveSign) {
			System.out.printf("%d %d %d", maxSum, 0, 0);
		} else {
			System.out.printf("%d %d %d", maxSum, a[0], a[N - 1]);

		}
	}


}
