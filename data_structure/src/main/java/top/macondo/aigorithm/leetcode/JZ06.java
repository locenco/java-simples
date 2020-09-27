package top.macondo.aigorithm.leetcode;

/**
 * 二分
 * @author: zhangchong
 * @Date: 2020/9/24 15:38
 **/
public class JZ06 {

	/**
	 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
	 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
	 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
	 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
	 *
	 * @param array
	 * @return
	 */
	public int minNumberInRotateArray(int[] array) {
		if (array.length == 0) {
			return 0;
		}
		/**
		 * 二分法。mid 大于high,往右找；小于high，往左找；等于high，往左-1；
		 * 找最小值，high移动的边界包含mid；
		 */
		int low = 0, high = array.length-1;
		while (low < high){
			int mid = (low + high) / 2;
			if(array[mid] > array[high]){
				low = mid + 1;
			}else if(array[mid] == array[high]){
				high = high - 1;
			}else{
				high = mid;
			}
		}
		return array[low];
	}
}
