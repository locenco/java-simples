package top.macondo.java.juc.threads.eventbus;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zhangchong
 * @Date: 2020/8/9 9:40
 **/
public class AsyncEventBus extends EventBus{

	public AsyncEventBus(String busName, EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
		super(busName, exceptionHandler, executor);
	}

	public AsyncEventBus(String busName,ThreadPoolExecutor executor) {
		this(busName,null, executor);
	}

	public AsyncEventBus(EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
		this("default-topic",exceptionHandler, executor);
	}

	public AsyncEventBus(ThreadPoolExecutor executor) {
		this("default-topic",null, executor);
	}
}
