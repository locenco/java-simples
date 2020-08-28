package top.macondo.java.juc.threads.evendriven.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zhangchong
 * @Date: 2020/8/12 14:54
 **/
@Configuration
@EnableAsync
public class AsyncListenerConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.initialize();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(1000);
		executor.setKeepAliveSeconds(300);
		executor.setThreadNamePrefix("async-executor-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return new AsyncTaskExecutorWrapper(executor);
	}

	class AsyncTaskExecutorWrapper implements AsyncTaskExecutor {

		private AsyncTaskExecutor executor;

		AsyncTaskExecutorWrapper(AsyncTaskExecutor executor) {
			this.executor = executor;
		}

		private Callable createCallable(final Callable task) {
			return new Callable() {
				@Override
				public Object call() throws Exception {
					try {
						return task.call();

					} catch (Exception e) {
						handleException(e);
						throw e;
					}
				}
			};
		}

		private Runnable createWrappedRunnable(final Runnable task) {
			return new Runnable() {
				@Override
				public void run() {
					try {
						task.run();
					} catch (Exception e) {
						handleException(e);
					}
				}
			};
		}

		private void handleException(Exception e) {
			System.out.println("Error during @Async execution: " + e);
		}

		@Override
		public void execute(Runnable task, long startTimeout) {
			executor.execute(createWrappedRunnable(task), startTimeout);
		}

		@Override
		public Future<?> submit(Runnable task) {
			return executor.submit(createWrappedRunnable(task));
		}

		@Override
		public <T> Future<T> submit(Callable<T> task) {
			return executor.submit(createCallable(task));
		}

		@Override
		public void execute(Runnable task) {
			executor.execute(task);
		}
	}
}
