package top.macondo.java.juc.atomic;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * zhangchong
 **/
public class AtomicTest {
	int a = 0;

	/**
	 * 多线程环境  atomic和基础类型
	 */
	@Test
	public void threadAtomicInteger(){
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		AtomicInteger atomicInteger = new AtomicInteger(0);
		for (int i = 0; i < 10000; i++) {
			executorService.execute(()->{
				atomicInteger.incrementAndGet();
				a++;
			});
		}
		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
			System.out.println(atomicInteger.get()); // = 10000
			System.out.println(a);   //<= 10000
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
