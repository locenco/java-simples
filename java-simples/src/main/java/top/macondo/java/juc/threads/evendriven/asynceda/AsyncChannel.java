package top.macondo.java.juc.threads.evendriven.asynceda;

import top.macondo.java.juc.threads.evendriven.eda.Channel;
import top.macondo.java.juc.threads.evendriven.eda.Event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zhangchong
 * @Date: 2020/8/8 22:52
 **/
public abstract class AsyncChannel implements Channel<Event> {
	private final ExecutorService executorService;

	public AsyncChannel() {
		this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
	}
	public AsyncChannel(ExecutorService executorService){
		this.executorService = executorService;
	}

	@Override
	public void dispatch(Event message) {
		executorService.submit(()->this.handle(message));
	}

	protected abstract void handle(Event message);
	void stop(){
		if(null != executorService && !executorService.isShutdown()){
			executorService.shutdown();
		}
	}
}
