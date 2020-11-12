package top.macondo.example.framework.guava;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.concurrent.*;

/**
 * @author: zhangchong
 * @Date: 2020/10/20 15:36
 **/
public class ListenalbeFutureTest {
	@Test
	public void testListenalbeFuture(){
		ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
		final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
			public Integer call() throws Exception {
				System.out.println("call execute..");
				TimeUnit.SECONDS.sleep(3);
				return 7;
			}
		});

		listenableFuture.addListener(()->{
			try {
				System.out.println(listenableFuture.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		},executorService);
		Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
			@Override
			public void onSuccess(@Nullable Integer integer) {
				//返回future的执行结果
				System.out.println("返回future的执行结果"+integer);
			}
			@Override
			public void onFailure(Throwable throwable) {

			}
		}, executorService);
		ListenableFuture<String> future = executorService.submit(() -> "hello, future");
		ListenableFuture<Integer> future2 = Futures.transform(future, String::length, executorService);
		//future2返回的是’hello, future‘的长度
		try {
			System.out.println("返回future的执行结果"+future2.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		SettableFuture<Integer> settableFuture = SettableFuture.create();
		ListenableFuture<Integer> future11 = executorService.submit(() -> {
			int sum = 5 + 6;
			settableFuture.set(sum);
			return sum;
		});
		// get设置超时时间
		try {
			System.out.println(settableFuture.get(2, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
