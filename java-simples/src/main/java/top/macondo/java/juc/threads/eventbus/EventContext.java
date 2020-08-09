package top.macondo.java.juc.threads.eventbus;

import java.lang.reflect.Method;

/**
 * @author: zhangchong
 * @Date: 2020/8/9 9:02
 **/
public interface EventContext {
	String getSource();
	Object getSubscriber();
	Method getSubscribe();
	Object getEvent();
}
