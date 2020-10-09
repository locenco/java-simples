package top.macondo.aigorithm.leetcode;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如
 *    矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 * @author: zhangchong
 * @Date: 2020/10/9 15:37
 **/
public class JZ65 {
	public static void main(String[] args) {
		JZ65 jz65 = new JZ65();
		System.out.println(jz65.hasPath("ABCESFCSADEE".toCharArray(),3,4,"SEE".toCharArray()));
	}
	public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
	{
		if (matrix == null) {
			return false;
		}
		boolean[] visited = new boolean[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] == str[0]
				&& hasPathHelper(matrix,rows,cols,i / cols,i % cols,str,0,visited)) {
					return true;
			}
		}
		return false;

	}
	public boolean hasPathHelper(char[] matrix, int rows, int cols, int curRow, int curCol, char[] str,int index, boolean[] visited)
	{
		int matrixIndex = curRow * cols + curCol;
		if(curRow < 0 || curCol < 0|| curRow >= rows || curCol >= cols || visited[matrixIndex]
				|| matrix[matrixIndex] != str[index]) {
			return false;
		}
		if (index == str.length-1) {
			return true;
		}
		visited[matrixIndex] = true;
		if (hasPathHelper(matrix, rows, cols, curRow + 1, curCol, str, index + 1, visited)
				|| hasPathHelper(matrix, rows, cols, curRow, curCol + 1, str, index + 1, visited)
				|| hasPathHelper(matrix, rows, cols, curRow - 1, curCol, str, index + 1, visited)
				|| hasPathHelper(matrix, rows, cols, curRow, curCol - 1, str, index + 1, visited)) {
			return true;
		}else {
			visited[matrixIndex] = false;
			return false;
		}
	}
}
