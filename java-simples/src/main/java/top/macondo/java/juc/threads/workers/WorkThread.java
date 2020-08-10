package top.macondo.java.juc.threads.workers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 11:03
 **/
public class WorkThread extends Thread{
	private static final Random random = new Random();
	private final Channel channel;

	public WorkThread(String name, Channel channel) {
		super(name);
		this.channel = channel;
	}

	@Override
	public void run() {
		while (true){
			channel.take().execute();
			try {
				TimeUnit.SECONDS.sleep(random.nextInt(10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
