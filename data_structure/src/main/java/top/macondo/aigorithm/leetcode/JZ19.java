package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * @author: zhangchong
 * @Date: 2020/9/25 11:01
 **/
public class JZ19 {
	public ArrayList<Integer> printMatrix(int[][] matrix) {
		int x = matrix[0].length;
		int y = matrix.length;
		int[] borders = {0, y - 1, 0, x - 1};
		int matrixSum = x * y;
		ArrayList<Integer> result = new ArrayList<>(matrixSum);
		printACircle(matrix, borders, result);
		return result;
	}

	public void printACircle(int[][] matrix, int[] borders, ArrayList<Integer> result) {
		int xLeft = borders[2], xRight = borders[3], yTop = borders[0], yBottom = borders[1];
		if (xLeft > xRight || yTop > yBottom) {
			return;
		} else if (xLeft == xRight || yTop == yBottom) {
			for (int i = xLeft; i <= xRight; i++) {
				result.add(matrix[yTop][i]);
			}
			for (int i = yTop + 1; i <= yBottom; i++) {
				result.add(matrix[i][xRight]);
			}
			return;
		} else {
			for (int i = xLeft; i <= xRight; i++) {
				result.add(matrix[yTop][i]);
			}
			for (int i = yTop + 1; i <= yBottom; i++) {
				result.add(matrix[i][xRight]);
			}
			for (int i = xRight - 1; i >= xLeft; i--) {
				result.add(matrix[yBottom][i]);
			}
			for (int i = yBottom - 1; i >= yTop + 1; i--) {
				result.add(matrix[i][xLeft]);
			}
			borders = new int[]{yTop + 1, yBottom - 1, xLeft + 1, xRight - 1};
			printACircle(matrix, borders, result);
		}


	}
}
