package top.macondo.java.juc.threads.immutable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 不可变对象设计模式
 * @author: zhangchong
 * @Date: 2020/8/7 9:03
 **/
public class ImmutableTest {
	@Test
	public void testListImmutable(){
		/**
		 * 声明成final，只能说明strList变量指向的地址空间不能改变但是该地址空间指向的内容是可以修改的。
		 */
		final List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		List<String> immutableList = Collections.unmodifiableList(list);
		immutableList.add("c");
	}
	final class Person {
		private final String name;
		private final String desc;

		public Person(String name, String desc) {
			this.name = name;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public String getName() {
			return name;
		}
	}
}
