package top.macondo.java.juc.threads.rwlock.demo;

/**
 * @author: zhangchong
 * @Date: 2020/8/6 17:50
 **/
public interface Lock {
	void Lock() throws InterruptedException;
	void unLock();
}
