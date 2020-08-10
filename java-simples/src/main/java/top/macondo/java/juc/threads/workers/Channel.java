package top.macondo.java.juc.threads.workers;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 11:02
 **/
public class Channel {
	private static final int MAX_REQUEST = 500;
	private final Request[] requestQueue;
	private final WorkThread[] workPool;
	private int head;
	private int tail;
	private int count;

	public Channel(int workers) {
		this.requestQueue = new Request[MAX_REQUEST];
		this.head = 0;
		this.tail = 0;
		this.count = 0;
		this.workPool = new WorkThread[workers];
		this.init();
	}

	private void init() {
		IntStream.range(0,workPool.length).forEach(i -> workPool[i] = new WorkThread("Worker-" + i, this));
	}
	public void startWorker(){
		Arrays.asList(workPool).forEach(WorkThread::start);
	}
	public synchronized void put(Request request){
		while (count >= requestQueue.length){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.requestQueue[tail] = request;
		this.tail = (this.tail + 1) % this.requestQueue.length;
		this.count++;
		this.notifyAll();
	}
	public synchronized Request take(){
		while (count <= 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Request request = this.requestQueue[head];
		this.head = (this.head + 1) % this.requestQueue.length;
		this.count--;
		this.notifyAll();
		return request;
	}
}
