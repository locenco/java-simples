package top.macondo.java.juc.threads.eventbus;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * @author: zhangchong
 * @Date: 2020/8/9 8:32
 **/
public class Subscriber {
	private final Object subscribeObject;
	private final Method subscribeMethod;
	private boolean disable = false;

	public Subscriber(Object subscribeObject, Method subscribeMethod) {
		this.subscribeObject = subscribeObject;
		this.subscribeMethod = subscribeMethod;
	}

	public Object getSubscribeObject() {
		return subscribeObject;
	}

	public Method getSubscribeMethod() {
		return subscribeMethod;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}
}
