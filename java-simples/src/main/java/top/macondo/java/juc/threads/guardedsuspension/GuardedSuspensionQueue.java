package top.macondo.java.juc.threads.guardedsuspension;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zhangchong
 * @Date: 2020/8/7 9:40
 **/
public class GuardedSuspensionQueue {
	private final LinkedList<Integer> queue = new LinkedList<>();
	private final int LIMIT = 10;

	public void offer(Integer data) throws InterruptedException {
		synchronized (this) {
			while (queue.size() > LIMIT) {
				this.wait();
			}
			queue.addLast(data);
			this.notifyAll();
		}
	}

	/**
	 * 获取元素
	 *
	 * @return
	 */
	public Integer take() throws InterruptedException {
		synchronized (this) {
			if (queue.isEmpty()) {
				this.wait();
			}
			this.notifyAll();
			return queue.removeFirst();
		}
	}

	public static void main(String[] args) {
		GuardedSuspensionQueue queue = new GuardedSuspensionQueue();
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				try {
					for (int j = 0; j < 100; j++) {
						queue.offer(j);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
		for (int i = 0; i < 1; i++) {
			new Thread(()->{
				Integer result;
				try {
					do {
						result = queue.take();

						System.out.println(result);

					}while (result != null);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}).start();
		}
	}
}
