package top.macondo.aigorithm.tool;

import java.util.Date;

/**
 * zhang chong
 **/
public class tomato {
	public static void main(String[] args) throws InterruptedException {
		Date now = new Date();
		long second = (System.currentTimeMillis()) % (30 * 60 * 1000);
		long waitTime = 30 * 60 * 1000 - second;
		System.out.println("等待下一番茄： " + waitTime / (1000 * 60) + "分钟");
		Thread.sleep(waitTime);
		for (int i = 1; i < 100; i++) {
			tomato(i);
		}

	}

	public static void tomato(int i) throws InterruptedException {
		java.awt.Toolkit.getDefaultToolkit().beep();
		System.out.println("开始番茄: " + i);
		Thread.sleep(25 * 60 * 1000);
		System.out.println("完成番茄: " + i);
		System.out.println("开始番茄休息: " + i);
		java.awt.Toolkit.getDefaultToolkit().beep();
		Thread.sleep(5 * 60 * 1000);
		System.out.println("完成番茄休息: " + i);
	}
}
