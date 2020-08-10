package top.macondo.java.juc.threads.eventbus.bus;


import top.macondo.java.juc.threads.eventbus.Dispatcher;
import top.macondo.java.juc.threads.eventbus.EventExceptionHandler;
import top.macondo.java.juc.threads.eventbus.Registry;
import top.macondo.java.juc.threads.eventbus.bus.Bus;

import java.util.concurrent.Executor;

/**
 * @author: zhangchong
 * @Date: 2020/8/9 8:22
 **/
public class EventBus implements Bus {
	private String busName;
	private final static String DEFAULT_BUS_NAME = "default";
	private static final String DEFAULT_TOPIC = "default-topic";

	private final Registry registry = new Registry();
	private final Dispatcher dispatcher;

	public EventBus() {
		this(DEFAULT_BUS_NAME, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
	}

	public EventBus(String busName) {
		this(busName, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
	}

	public EventBus(EventExceptionHandler exceptionHandler) {
		this(DEFAULT_BUS_NAME, exceptionHandler, Dispatcher.SEQ_EXECUTOR_SERVICE);
	}

	public EventBus(String busName, EventExceptionHandler exceptionHandler, Executor executor) {
		this.busName = busName;
		this.dispatcher = Dispatcher.newDispatcher(exceptionHandler, executor);
	}


	@Override
	public void register(Object subscriber) {
		this.registry.bind(subscriber);
	}

	@Override
	public void unregister(Object subscriber) {
		this.registry.unbind(subscriber);
	}

	@Override
	public void post(Object event) {
		post(event, DEFAULT_TOPIC);
	}

	@Override
	public void post(Object event, String topic) {
		this.dispatcher.dispatch(this, registry, event, topic);

	}

	@Override
	public void close() {
		this.dispatcher.close();
	}

	@Override
	public String getBusName() {
		return this.busName;
	}
}
