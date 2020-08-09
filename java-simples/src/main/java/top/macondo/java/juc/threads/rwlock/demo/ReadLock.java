package top.macondo.java.juc.threads.rwlock.demo;

/**
 * @author: zhangchong
 * @Date: 2020/8/6 20:19
 **/
class ReadLock implements Lock {
	private final ReadWriteLockImpl readWriteLock;

	public ReadLock(ReadWriteLockImpl readWriteLock) {
		this.readWriteLock = readWriteLock;
	}

	@Override
	public void Lock() throws InterruptedException {
		synchronized (readWriteLock.getMUTEX()){
			while (readWriteLock.getWritingWriters() > 0
					&& (readWriteLock.  getPreferWriter() && readWriteLock.getWaitingWriters() > 0)
			){
				readWriteLock.getMUTEX().wait();
			}
			readWriteLock.incrementReadingReaders();
		}
	}

	@Override
	public void unLock() {
		synchronized (readWriteLock.getMUTEX()){
			readWriteLock.decrementReadingWriters();
			readWriteLock.changePrefer(true);
			readWriteLock.getMUTEX().notifyAll();
		}
	}
}
