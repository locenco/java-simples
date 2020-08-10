package top.macondo.java.juc.threads.eventbus;

import top.macondo.java.juc.threads.eventbus.subscribe.Subscribe;
import top.macondo.java.juc.threads.eventbus.subscribe.Subscriber;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: zhangchong
 * @Date: 2020/8/9 8:23
 **/
public class Registry {
	private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>>
	subscriberContainer = new ConcurrentHashMap<>();

	public void bind(Object subscriber){
		List<Method> subscribeMethods = getSubscribeMethods(subscriber);
		subscribeMethods.forEach(method -> tierSubscriber(subscriber,method));
	}
	public void unbind(Object subscriber){
		subscriberContainer.forEach((key,queue) -> queue.forEach(subscriber1 -> {
			if (subscriber1.getSubscribeObject() == subscriber){
				subscriber1.setDisable(true);
			}
		}));
	}

	public ConcurrentLinkedQueue<Subscriber> scanSubscriber(final String topic){
		return subscriberContainer.get(topic);
	}

	/**
	 * 获得类及父类中的@Subscribe 方法
	 * @param subscriber
	 * @return
	 */
	private List<Method> getSubscribeMethods(Object subscriber){
		final List<Method> methods = new ArrayList<>();
		Class<?> temp = subscriber.getClass();
		while (temp != null){
			Method[] declaredMethods = temp.getDeclaredMethods();
			Arrays.stream(declaredMethods)
					.filter(m -> m.isAnnotationPresent(Subscribe.class)
					         && m.getParameterCount() == 1
					         && m.getModifiers() == Modifier.PUBLIC)
					.forEach(methods::add);
			temp = temp.getSuperclass();
		}
		return methods;
	}
	private void tierSubscriber(Object subscriber, Method method){
		final Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
		String topic = subscribe.topic();
		subscriberContainer.computeIfAbsent(topic,key->new ConcurrentLinkedQueue<>());
		subscriberContainer.get(topic).add(new Subscriber(subscriber,method));
	}
}
