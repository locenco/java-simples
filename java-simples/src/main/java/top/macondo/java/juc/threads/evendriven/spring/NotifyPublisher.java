package top.macondo.java.juc.threads.evendriven.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: zhangchong
 * @Date: 2020/8/12 14:40
 **/
@Component
public class NotifyPublisher implements ApplicationContextAware {
	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}

	public void publishEvent(int status, String msg) {
		ctx.publishEvent(new NotifyEvent(this, msg));
	}
}
