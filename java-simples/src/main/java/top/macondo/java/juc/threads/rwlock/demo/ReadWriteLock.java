package top.macondo.java.juc.threads.rwlock.demo;

/**
 * @author: zhangchong
 * @Date: 2020/8/6 17:51
 **/
public interface ReadWriteLock {
	Lock readLock();
	Lock writeLock();
	int getWritingWriters();
	int getReadingReaders();
	int getWaitingWriters();
	static ReadWriteLock readWriteLock(){
		return new ReadWriteLockImpl();
	}
	static ReadWriteLock readWriteLock(boolean preferWriter){
		return new ReadWriteLockImpl(preferWriter);
	}
}
