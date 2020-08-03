package top.macondo.java.stream;

import cn.hutool.core.util.StrUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: zhangchong
 * @Date: 10:30
 **/
public class StreamTest {
	@Test
	public void testIntStream(){
		List<Integer> list= IntStream.rangeClosed(1,32).boxed().collect(Collectors.toList());
		System.out.println(list.size());
	}
	@Test
	public void testAnyMatch(){
		boolean result = Stream.of(Optional.ofNullable("0,100,101").orElse("").split(StrUtil.COMMA))
				.anyMatch(x -> x.equals(new Long(100).toString()));
		Assert.assertTrue(result);
		result = Stream.of(Optional.ofNullable("0,100,101").orElse("").split(StrUtil.COMMA))
					.anyMatch(x -> new Long(100).equals(x));
		Assert.assertFalse(result);
	}
}
