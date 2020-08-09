package top.macondo.java.juc.threads.rwlock.demo;

import static java.lang.Thread.currentThread;

/**
 * @author: zhangchong
 * @Date: 2020/8/6 20:49
 **/
public class ReadWriteLockTest {
	private static final String text = "example_data";

	public static void main(String[] args) {
		final ShareData shareData = new ShareData(50);
		for (int i = 0; i < 2; i++) {
			new Thread(()->{
				for (int j = 0; j < text.length(); j++) {
					try {
						char c = text.charAt(j);
						shareData.write(c);
						System.out.println(currentThread() + " write " + c);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		for (int i = 0; i < 10; i++) {
			new Thread(()->{
				while (true){
					try {
						System.out.println(currentThread() + " read " + new String(shareData.read()));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
