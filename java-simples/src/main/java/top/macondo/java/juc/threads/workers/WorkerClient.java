package top.macondo.java.juc.threads.workers;

import java.util.stream.IntStream;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 11:24
 **/
public class WorkerClient {
	public static void main(String[] args) {
		final Channel channel = new Channel(10);
		channel.startWorker();
		new ClientThread("A",channel).start();
		new ClientThread("B",channel).start();
		new ClientThread("C",channel).start();
	}
}
