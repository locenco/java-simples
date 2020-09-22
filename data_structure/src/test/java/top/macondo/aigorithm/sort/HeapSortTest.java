package top.macondo.aigorithm.sort;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author: zhangchong
 * @Date: 2020/9/22 15:04
 **/
public class HeapSortTest {

	@Test
	public void testHeapSort() {
		int[] randomArr = {40,70,50,30,80,90,10,20,60};
		HeapSort.heapSort(randomArr);
		for (int i = 1; i <= 9; i++) {
			assertEquals(randomArr[i-1], i * 10);
		}
	}
}