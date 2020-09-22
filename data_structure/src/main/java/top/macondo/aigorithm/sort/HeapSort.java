package top.macondo.aigorithm.sort;

/**
 * 堆排
 */
public class HeapSort {

	/**
	 * 堆排序
	 */
	public static void heapSort(int[] arr) {
		int n = arr.length;
		// 将待排序的序列构建成一个大顶堆
		/**
		 * 在数据中，a.length/2+1一直到最后的元素都是叶子元素，也就是平凡最大堆，因此从其前一个元素开始，一直到
		 * 第一个元素
		 * 自底向上构建堆
		 */
		for (int i = n / 2; leftChild(i) >= 0; i--){
			heapAdjust(arr, i, n);
		}

		// 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆
		for (int i = n-1; i > 0; i--){
			swap(arr, 0,i);
			heapAdjust(arr,0,i);
		}
	}
 
	/**
	 * 构建堆的过程
	 * 此过程是在子节点是堆，根节点不满足堆的情况
	 * @param arr 需要排序的数组
	 * @param i 需要构建堆的根节点的序号
	 * @param n 数组的长度
	 */
	private static void heapAdjust(int[] arr, int i, int n) {
		int root = arr[i];
		//判断未越界
		while (leftChild(i) < n){
			//1. 两个孩子节点取较大
			int childIndex = leftChild(i);
			if(childIndex + 1 < n && arr[childIndex + 1] > arr[childIndex]){
				childIndex ++;
			}
			//2. 孩子节点和根节点比较，大于根节点时就覆盖它的父节点。
			if(root < arr[childIndex]){
				arr[i] = arr[childIndex];
			}else {
				break;
			}
			//3. 索引标记移动到孩子节点（此时移动到的子节点，数据已经覆盖到它的父节点），重复1.2.操作
			i = childIndex;
		}
		//4. 最后将根节点安放在最后标记的节点
		arr[i] = root;

	}

	/**
	 * 	获取到左孩子结点
 	 */
	private static int leftChild(int i) {
		return 2 * i + 1;
	}
	
	/*
	 * 交换元素位置
	 */
	private static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}
}