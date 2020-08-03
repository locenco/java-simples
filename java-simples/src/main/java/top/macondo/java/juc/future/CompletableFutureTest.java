package top.macondo.java.juc.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
	public static void main(String[] args) {
	}

	@Test
	public void testCompletableFuture() {
		System.out.println("start");
		/**
		 * 异步非阻塞
		 */
		CompletableFuture.runAsync(() -> {
			try {
				Thread.sleep(3000);
				System.out.println("sleep done");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		CompletableFuture.supplyAsync(() -> "result").thenAccept(r -> {
			System.out.println(r+" step then one");
		}).thenAccept(r -> {
			System.out.println(r+" step then two");
		});
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("done");
	}
}