package top.macondo.java.mockito;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * zhangchong
 **/
public class MockObjectTest {
	@Test
	public void configMockObject() {
		List mockedList = mock(List.class);
		// 我们定制了当调用 mockedList.add("one") 时, 返回 true
		when(mockedList.add("one")).thenReturn(true);
		// 当调用 mockedList.size() 时, 返回 1
		when(mockedList.size()).thenReturn(1);
		Assert.assertTrue(mockedList.add("one"));
		// 因为我们没有定制 add("two"), 因此返回默认值, 即 false.
		Assert.assertFalse(mockedList.add("two"));
		Assert.assertEquals(mockedList.size(), 1);
		Iterator i = mock(Iterator.class);
		when(i.next()).thenReturn("Hello,").thenReturn("Mockito!");
		String result = i.next() + " " + i.next();
		//assert
		Assert.assertEquals("Hello, Mockito!", result);
	}
	@Test
	public void testVerify() {
		List mockedList = mock(List.class);
		mockedList.add("one");
		mockedList.add("two");
		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");
		when(mockedList.size()).thenReturn(5);
		Assert.assertEquals(mockedList.size(), 5);
		verify(mockedList, atLeastOnce()).add("one");//第一句校验 mockedList.add("one") 至少被调用了 1 次(atLeastOnce)
		verify(mockedList, times(1)).add("two");//第二句校验 mockedList.add("two") 被调用了 1 次(times(1))
		verify(mockedList, times(3)).add("three times");//第三句校验 mockedList.add("three times") 被调用了 3 次(times(3))
		verify(mockedList, never()).isEmpty();//第四句校验 mockedList.isEmpty() 从未被调用(never)
	}




}
