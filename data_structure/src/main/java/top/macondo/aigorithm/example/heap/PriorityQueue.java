package top.macondo.aigorithm.example.heap;

/**
 * zhang chong
 **/
public class PriorityQueue<T extends Comparable> {

	T[] array;
	int currentSize = 0;

	/**
	 * 二叉树 插入上滤
	 */
	public void insert(T X) {
		//判断数组是否需要扩容
		if (currentSize == array.length - 1) {
			enlargeArray(array.length * 2 + 1);
		}
		//循环判断X应放入的空穴
		int hole = ++currentSize;
		while (hole > 1 && X.compareTo(array[hole / 2]) < 0) {
			array[hole] = array[hole / 2];
			hole /= 2;
		}
		array[hole] = X;
	}

	/**
	 * 数组扩容
	 *
	 * @param i
	 */
	private void enlargeArray(int i) {
	}


}
