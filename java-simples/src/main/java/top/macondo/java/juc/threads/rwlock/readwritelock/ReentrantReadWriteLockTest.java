package top.macondo.java.juc.threads.rwlock.readwritelock;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

import static java.lang.Thread.currentThread;

public class ReentrantReadWriteLockTest {
	private final ReadWriteLock rwlock = new ReentrantReadWriteLock(false);
	private final Lock rlock = rwlock.readLock();
	private final Lock wlock = rwlock.writeLock();
	private int[] counts = new int[10];

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
		for (int i = 0; i < 6; i++) {
			new Thread(() -> {
				for (int j = 0; j < 10; j++) {
					test.inc(j);
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				System.out.print("read: ");
				for (int j : test.get()) {
					System.out.print(j);
				}
				System.out.println();
			}).start();
		}

	}

	public void inc(int index) {
		wlock.lock(); // 加写锁
		try {
			counts[index] += 1;
		} finally {
			wlock.unlock(); // 释放写锁
		}
	}

	public int[] get() {
		rlock.lock(); // 加读锁
		try {
			return Arrays.copyOf(counts, counts.length);
		} finally {
			rlock.unlock(); // 释放读锁
		}
	}
}