package top.macondo.aigorithm.sort;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author: zhangchong
 * @Date: 2020/9/22 15:04
 **/
public class SortTest {
	int[] randomArr = {40,70,50,30,80,90,10,20,60};

	@Test
	public void testHeapSort() {
		HeapSort.heapSort(randomArr);
		assertEqualsResult(randomArr);
	}

	@Test
	public void testQuickSort(){
		QuickSort.quickSort(randomArr);
		assertEqualsResult(randomArr);
	}
	@Test
	public void testBubbleSort(){
		BubbleSort.bubbleSort(randomArr);
		assertEqualsResult(randomArr);
	}
	@Test
	public void testSelectSort(){
		SelectSort.selectSort(randomArr);
		assertEqualsResult(randomArr);
	}
	private void assertEqualsResult(int[] randomArr){
		for (int i = 1; i <= 9; i++) {
			assertEquals(randomArr[i-1], i * 10);
		}
	}

}