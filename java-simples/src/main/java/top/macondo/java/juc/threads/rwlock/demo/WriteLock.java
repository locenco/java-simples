package top.macondo.java.juc.threads.rwlock.demo;

/**
 * @author: zhangchong
 * @Date: 2020/8/6 20:20
 **/
class WriteLock implements Lock {
	private final ReadWriteLockImpl readWriteLock;

	public WriteLock(ReadWriteLockImpl readWriteLock) {
		this.readWriteLock = readWriteLock;
	}

	@Override
	public void Lock() throws InterruptedException {
		synchronized (readWriteLock.getMUTEX()){
			try {
				readWriteLock.incrementWaitingWriters();
				while (readWriteLock.getReadingReaders() > 0
						&& readWriteLock.getWritingWriters() > 0){
					readWriteLock.getMUTEX().wait();
				}
			}finally {
				this.readWriteLock.decrementWaitingWriters();
			}
			readWriteLock.incrementWritingWriters();

		}
	}

	@Override
	public void unLock() {
		synchronized (readWriteLock.getMUTEX()){
			readWriteLock.decrementWritingWriters();
			readWriteLock.changePrefer(false);
			readWriteLock.getMUTEX().notifyAll();
		}
	}
}
