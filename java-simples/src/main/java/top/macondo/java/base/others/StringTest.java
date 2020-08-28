package top.macondo.java.base.others;

import org.junit.Test;

/**
 * @author: zhangchong
 * @Date: 2020/8/28 12:29
 **/
public class StringTest {
	/**
	 * 执行速度StringBuilder -> StringBuffer -> String
	 */
	@Test
	public void testString(){
		String str = "";
		long beginTime = System.currentTimeMillis();

		for (int i = 0; i < 999999; i++){
			str = str + i;
		}

		long endTime = System.currentTimeMillis();

		System.out.println("执行时间：" + (endTime - beginTime)/1000);
	}
	@Test
	public void testStringBuffer(){
		StringBuffer buffer = new StringBuffer("");
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < 999999; i++){
			buffer.append(i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间：" + (endTime - beginTime));

	}
	@Test
	public void testStringBuilder(){
		StringBuilder builder = new StringBuilder("");
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < 99999; i++){
			builder.append(i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间：" + (endTime - beginTime));
	}
}
