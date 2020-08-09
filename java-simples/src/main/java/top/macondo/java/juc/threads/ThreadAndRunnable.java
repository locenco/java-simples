package top.macondo.java.juc.threads;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: zhangchong
 * @Date: 2020/8/3 19:51
 **/
public class ThreadAndRunnable {
	@Test
	public void testRunnable() {
		Runnable runnable = new Runnable() {
			int i = 0;
			public static final int MAX = 50;

			@Override
			public void run() {
				while (i < MAX) {
					System.out.println(Thread.currentThread() + "的号码是:" + i++);
				}
			}
		};
		Thread t1 = new Thread(runnable, "t1");
		Thread t2 = new Thread(runnable, "t2");
		Thread t3 = new Thread(runnable, "t3");
		Thread t4 = new Thread(runnable, "t4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

	@Test
	public void testThreadName() {
		IntStream.rangeClosed(1, 10).boxed()
				.map(x -> new Thread(() -> System.out.println(Thread.currentThread().getName())))
				.forEach(Thread::start);
	}

	@Test
	public void testParentThread() {
		Thread thread = new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName());
				Thread.sleep(3000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "test");
		System.out.println(Thread.currentThread().getName());
		thread.start();
		System.out.println(Thread.currentThread().getName());
	}

	@Test
	public void testGroup() {
		Thread t1 = new Thread("t1");
		ThreadGroup testGroup = new ThreadGroup("test");
		Thread t2 = new Thread(testGroup, "t2");
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		Assert.assertTrue(t1.getThreadGroup() == mainGroup);
		Assert.assertFalse(t2.getThreadGroup() == mainGroup);
		Assert.assertFalse(t1.getThreadGroup() == testGroup);
		Assert.assertTrue(t2.getThreadGroup() == testGroup);
	}

	@Test
	public void testDaemon() throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						Assert.assertFalse(isInterrupted());
					}
				}
			}
		};
		t1.setDaemon(true);
		t1.start();
		Assert.assertFalse(t1.isInterrupted());
		TimeUnit.SECONDS.sleep(2);

		t1.interrupt();
		TimeUnit.SECONDS.sleep(1);
		Assert.assertFalse(t1.isInterrupted());

	}

	public static void main(String[] args) throws InterruptedException {
		List<Thread> threads = IntStream.range(1, 3)
				.mapToObj(ThreadAndRunnable::create).collect(Collectors.toList());
		threads.forEach(Thread::start);
		for (Thread thread : threads) {
			thread.join();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + "#" + i);
		}
	}

	public static Thread create(int seq) {
		return new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + "#" + i);

			}
		}, String.valueOf(seq));
	}

}
