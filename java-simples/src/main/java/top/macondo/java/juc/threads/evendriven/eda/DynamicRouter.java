package top.macondo.java.juc.threads.evendriven.eda;

/**
 * @author: zhangchong
 * @Date: 2020/8/8 22:30
 **/
public interface DynamicRouter<E extends Message> {
	void registerChannel(Class<? extends E> messageType, Channel<? extends E> channel);
	void dispatch(E message);
}
