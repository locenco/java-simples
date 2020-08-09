package top.macondo.java.juc.threads.eventbus;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zhangchong
 * @Date: 2020/8/9 8:53
 **/
public class SimpleObject {
	@Subscribe(topic = "alex-topic")
	public void test2(Integer x){
		System.out.println("alex-topic-test2: " + x);
	}
	@Subscribe(topic = "test-topic")
	public void test3(Integer x){
		System.out.println("test-topic-test3: " + x);
	}

	public static void main(String[] args) {
		Bus bus = new EventBus("TestBus");
		bus.register(new SimpleObject());
		bus.post("test");
		bus.post(1,"alex-topic");
		Bus bus1 = new AsyncEventBus("TestBus1", (ThreadPoolExecutor) Executors.newFixedThreadPool(10));
		bus1.register(new SimpleObject());
		bus1.post(1);
		bus1.post(1,"alex-topic");

	}
}
