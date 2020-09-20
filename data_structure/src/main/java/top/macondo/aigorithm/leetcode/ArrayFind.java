package top.macondo.aigorithm.leetcode;

/**
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * Zhang Chong
 * 2020/1/19 10:17
 **/
public class ArrayFind {
	public Boolean Find(int target, int[][] matrix) {
		if (matrix == null) {
			return false;
		}
		String a = "asd";
		if (a == "asd") {
			System.out.println("123");
		}

		int rows = matrix.length, cols = matrix[0].length;
		//标记到数组右上角
		int posRow = 0, posCol = cols - 1;
		/**
		 * target大于标记位，标记下移，小于左移。等于返回
		 */
		while (posRow <= rows - 1 && posCol >= 0) {
			if (target == matrix[posRow][posCol]) {
				return true;
			} else if (target > matrix[posRow][posCol]) {
				posRow++;
			} else {
				posCol--;
			}
		}

		return false;
	}
}
