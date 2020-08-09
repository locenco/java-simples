package top.macondo.java.juc.threads.threadlocal;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * @author:zhangchong
 * @Date:2020/8/7 10:19
 */
public class ThreadLocalTest {
	@Test
	public static void main(String[] args) {
		ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
		IntStream.range(0, 10)
				.forEach(i -> new Thread(() -> {
					try {

						threadLocal.set(i);
						System.out.println(currentThread() + " set i " + threadLocal.get());
						TimeUnit.SECONDS.sleep(1);
						System.out.println(currentThread() + " get i " + threadLocal.get());
					} catch (InterruptedException e) {


					}
				}).start());
	}

	public static class ThreadId {
		// Atomic integer containing the next thread ID to be assigned
		private static final AtomicInteger nextId = new AtomicInteger(0);

		// Thread local variable containing each thread's ID
		private static final ThreadLocal<Integer> threadId =
				new ThreadLocal<Integer>() {
					@Override
					protected Integer initialValue() {
						return nextId.getAndIncrement();
					}
				};

		// Returns the current thread's unique ID, assigning it if necessary
		public static int get() {
			return threadId.get();
		}
	}
}
