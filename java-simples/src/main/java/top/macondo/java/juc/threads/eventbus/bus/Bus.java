package top.macondo.java.juc.threads.eventbus.bus;

/**
 * @author: zhangchong
 * @Date: 2020/8/9 8:17
 **/
public interface Bus {
	void register(Object subscriber);
	void unregister(Object subscriber);
	void post(Object event);
	void post(Object event, String topic);
	void close();
	String getBusName();
}
