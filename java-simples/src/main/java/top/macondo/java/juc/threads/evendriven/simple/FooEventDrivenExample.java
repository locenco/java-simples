package top.macondo.java.juc.threads.evendriven.simple;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: zhangchong
 * @Date: 2020/8/8 22:22
 **/
public class FooEventDrivenExample {
	public static void handleEventA(Event e){
		System.out.println(e.getData().toLowerCase());
	}
	public static void handleEventB(Event e){
		System.out.println(e.getData().toUpperCase());
	}

	public static void main(String[] args) {
		Queue<Event> events = new LinkedList<>();
		events.add(new Event("A","Hello"));
		events.add(new Event("B","World"));
		Event event;
		while (!events.isEmpty()){
			event = events.remove();
			switch (event.getType()){
				case "A":
					handleEventA(event);
					break;
				case "B":
					handleEventB(event);
					break;
			}
		}
	}
}
