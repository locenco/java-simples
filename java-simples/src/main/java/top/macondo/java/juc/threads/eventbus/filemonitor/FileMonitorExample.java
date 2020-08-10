package top.macondo.java.juc.threads.eventbus.filemonitor;

import top.macondo.java.juc.threads.eventbus.bus.AsyncEventBus;
import top.macondo.java.juc.threads.eventbus.bus.EventBus;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zhangchong
 * @Date: 2020/8/9 14:48
 **/
public class FileMonitorExample {
	public static void main(String[] args) throws IOException {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
		final EventBus eventBus = new AsyncEventBus(executor);
		eventBus.register(new FileChangeListener());
		DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus,"E:\\tmp\\test\\key1");
		monitor.startMonitor();
	}
}
