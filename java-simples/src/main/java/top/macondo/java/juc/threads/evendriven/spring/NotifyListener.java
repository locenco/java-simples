package top.macondo.java.juc.threads.evendriven.spring;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author: zhangchong
 * @Date: 2020/8/12 14:44
 **/
@Component
public class NotifyListener {
	@EventListener
	@Async
	public void listen(NotifyEvent notifyEvent){
		System.out.println(Thread.currentThread().getName()+ " notifyEvent = " + notifyEvent.getMsg());
	}
}
