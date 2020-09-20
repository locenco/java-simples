package top.macondo.aigorithm.other;

import java.util.Scanner;

/**
 * zhangchong
 * 2020/2/28 17:51
 **/
public class ReversingLinkedList {
	private static Scanner in = new Scanner(System.in);
	private static final int MAX_SIZE = 100000;

	public static void main(String[] args) {

		int[] data = new int[MAX_SIZE];
		int[] next = new int[MAX_SIZE];
		int[] list = new int[MAX_SIZE];
		//1.输入
		int firstNode = in.nextInt();
		int number = in.nextInt();
		int K = in.nextInt();
		inputData(number, data, next);

		//2、整理顺序
		int index = 0;
		while (firstNode != -1) {
			list[index++] = firstNode;
			firstNode = next[firstNode];
		}
		list[index] = -1;

		for (int i = K; i <= number; i += K) {
			for (int j = 0; j < K / 2; j++) {
				int temp = list[i - 1 - j];
				list[i - 1 - j] = list[i - K + j];
				list[i - K + j] = temp;
			}
		}

		for (int i = 0; i < number; i++) {
			if (list[i + 1] == -1) {
				System.out.printf("%05d %d %d\n", list[i], data[list[i]], list[i + 1]);
			} else {
				System.out.printf("%05d %d %05d\n", list[i], data[list[i]], list[i + 1]);
			}
		}
	}

	private static void inputData(int number, int[] data, int[] next) {
		int i = 0;
		while (i++ < number) {
			int indexNode = in.nextInt();
			int dataNode = in.nextInt();
			int nextNode = in.nextInt();
			data[indexNode] = dataNode;
			next[indexNode] = nextNode;
		}
	}

}
