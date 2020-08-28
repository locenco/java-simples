package top.macondo.java.juc.threads.evendriven.spring;

import org.springframework.context.ApplicationEvent;

/**
 * @author: zhangchong
 * @Date: 2020/8/12 14:38
 **/
public class NotifyEvent extends ApplicationEvent {
	private String msg;
	/**
	 * Create a new {@code ApplicationEvent}.
	 *
	 * @param source the object on which the event initially occurred or with
	 *               which the event is associated (never {@code null})
	 */
	public NotifyEvent(Object source, String msg) {
		super(source);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
