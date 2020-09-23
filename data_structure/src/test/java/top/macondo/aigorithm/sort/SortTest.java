package top.macondo.aigorithm.sort;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author: zhangchong
 * @Date: 2020/9/22 15:04
 **/
public class SortTest {
	int[] randomArr = {40, 70, 50, 30, 80, 90, 10, 20, 60};
	Sort sort;
	@Test
	public void testHeapSort() {
		sort = new HeapSort();
		sort.sort(randomArr);
		assertEqualsResult(randomArr);
	}

	@Test
	public void testQuickSort(){
		sort = new QuickSort();
		sort.sort(randomArr);
		assertEqualsResult(randomArr);
	}
	@Test
	public void testBubbleSort(){
		sort = new BubbleSort();
		sort.sort(randomArr);
		assertEqualsResult(randomArr);
	}
	@Test
	public void testSelectSort(){
		sort = new SelectSort();
		sort.sort(randomArr);
		assertEqualsResult(randomArr);
	}
	@Test
	public void testInsertSort(){
		sort = new InsertSort();
		sort.sort(randomArr);
		assertEqualsResult(randomArr);
	}
	@Test
	public void testShellSort(){
		sort = new ShellSort();
		sort.sort(randomArr);
		assertEqualsResult(randomArr);
	}
	@Test
	public void testMergeSort(){
		int[] result = MergeSort.sortByReturn(randomArr);
		assertEqualsResult(result);
	}
	private void assertEqualsResult(int[] randomArr){
		for (int i = 1; i <= 9; i++) {
			assertEquals(randomArr[i-1], i * 10);
		}
	}

}