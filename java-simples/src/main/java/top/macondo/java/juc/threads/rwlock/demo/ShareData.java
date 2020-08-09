package top.macondo.java.juc.threads.rwlock.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhangchong
 * @Date: 2020/8/6 20:42
 **/
public class ShareData {
	private final List<Character> container = new ArrayList<>();
	private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();
	private final Lock readLock = readWriteLock.readLock();
	private final Lock writeLock = readWriteLock.writeLock();
	private final int length;

	public ShareData(int length) {
		this.length = length;
		for (int i = 0; i < length; i++) {
			container.add(i, 'c');
		}
	}
	public char[] read() throws InterruptedException {
		try{
			readLock.Lock();
			char[] newBuffer = new char[length];
			for (int i = 0; i < length; i++) {
				newBuffer[i] = container.get(i);
			}
			slowly();
			return newBuffer;
		}finally {
			readLock.unLock();
		}
	}
	public void write(char c) throws InterruptedException {
		try {
			writeLock.Lock();
			for (int i = 0; i < length; i++) {
				this.container.add(i,c);
			}
			slowly();
		}finally {
			writeLock.unLock();
		}
	}

	private void slowly() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
