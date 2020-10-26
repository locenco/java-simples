package top.macondo.aigorithm.newcoder;

import java.util.Arrays;

/**
 * @author: zhangchong
 * @Date: 2020/10/11 9:43
 **/
public class NC501 {
	public static void main(String[] args) {
		NC501 nc501 = new NC501();
		long result = nc501.minimumValueAfterDispel(new int[] {
		    1, 3, 2, 0, 3
		});
		System.out.println(result);
	}
	/**
	 * 返回两次操作后，数组元素之和的最小值
	 * @param nums int整型一维数组 这你你需要操作的数组
	 * @return long长整型
	 */
	public long minimumValueAfterDispel (int[] nums) {
		int[] minimumValuesStep1 = minimumValueAfterDispelHelper(nums);
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if(nums[i] >= minimumValuesStep1[0]){
				nums[i] -= minimumValuesStep1[0];
			}
		}
		int[] minimumValuesStep2 = minimumValueAfterDispelHelper(nums);
		return sum - minimumValuesStep1[0] * minimumValuesStep1[1] - minimumValuesStep2[0] * minimumValuesStep2[1];
	}

	public int[] minimumValueAfterDispelHelper(int[] nums) {
		int[] minimumValues = new int[2];
		for (int i = 0; i < nums.length; i++) {
			int[] minimumValuesTemp = new int[2];

			minimumValuesTemp[0] = nums[i];
			for (int j = 0; j < nums.length; j++) {
				if (nums[j] >= nums[i]) {
					minimumValuesTemp[1]++;
				}
			}
			if (minimumValuesTemp[0] * minimumValuesTemp[1] > minimumValues[0] * minimumValues[1]) {
				minimumValues[0] = minimumValuesTemp[0];
				minimumValues[1] = minimumValuesTemp[1];
			}else if(minimumValuesTemp[0] * minimumValuesTemp[1] == minimumValues[0] * minimumValues[1] &&
					minimumValues[1] <= minimumValuesTemp[1]
			){
				minimumValues[0] = minimumValuesTemp[0];
				minimumValues[1] = minimumValuesTemp[1];
			}
		}
		return minimumValues;
	}

	/**
	 * 网上答案
	 */
	public long minimumValueAfterDispelCopy(int[] nums) {
		// write code here
		Arrays.sort(nums);
		long sum = 0;//记录整个数组的和
		long max = 0;//记录能够减去的最大值
		for(int j=0;j<nums.length;j++){
			sum += nums[j];
			int index1 = j;
			int index2 = j;
			int index3 = j;
			for(int i=0;i<=j;i++){
				while(index1 > 0 && nums[index1-1] >= nums[j]-nums[i]){
					index1--;
				}
				while(index2 > i && nums[index2-1] >= nums[j]-nums[i]){
					index2--;
				}
				while(index3 < nums.length && (long)nums[index3] < (long)nums[i]+nums[j]){
					index3++;
				}
                /*
                假设两次减去的数为a,b(a<b)总共分为三种情况  i < j
                1.a=nums[j]-nums[i]    b=nums[i]
                2.a=nums[i]    b=nums[j]-nums[i]
                3.a=nums[i]    b=nums[j]
                分段函数的边界:
                1.  index1 < i < j < nums.length  其实index1大于i时不碍事 i-index1变成负数不会影响max的计算
                2.  i < index2 < j < nums.length
                3.  i < j < index3 < nums.length
                对于第一种情况 index1到i之间的数只能减去a 即 nums[j]-nums[i], i到j之间的数只能减去b 即nums[i] , j到最后的数可以减去a+b 即nums[j]
                对于第二种情况 i到index2之间的数只能减去a 即 nums[i], index2到j之间的数只能减去b 即nums[j]-nums[i] , j到最后的数可以减去a+b 即nums[j]
                对于第三种情况 i到j之间的数只能减去a 即 nums[i], j到index3之间的数只能减去b 即nums[j], index3到最后的数可以减去a+b 即nums[j]+nums[i]

                另外一个答案的版本之所以跟这个不一样是因为他给表达式化简了.......
                 */
				long tmp1 = (i-index1)*((long)nums[j]-nums[i]) + (j-i)*(long)nums[i] + (nums.length-j)*(long)nums[j];
				long tmp2 = (index2-i)*((long)nums[i]) + (j-index2)*((long)nums[j]-nums[i]) + (nums.length-j)*(long)nums[j];
				long tmp3 = (j-i)*(long)nums[i] + (index3-j)*(long)nums[j] + (nums.length-index3)*((long)nums[i]+nums[j]);
				max = Math.max(max,tmp1);
				max = Math.max(max,tmp2);
				max = Math.max(max,tmp3);
			}
		}
		return sum - max;
	}

}
