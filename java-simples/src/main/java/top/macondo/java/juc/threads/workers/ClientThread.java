package top.macondo.java.juc.threads.workers;

import java.util.Random;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 11:20
 **/
public class ClientThread extends Thread {
	private static final Random random = new Random(System.currentTimeMillis());
	private final Channel channel;

	public ClientThread(String name, Channel channel) {
		super(name);
		this.channel = channel;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; true; i++){
				Request request = new Request(getName(),i);
				this.channel.put(request);
				Thread.sleep(random.nextInt(1_000));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
