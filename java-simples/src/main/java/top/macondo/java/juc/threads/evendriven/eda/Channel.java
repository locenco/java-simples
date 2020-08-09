package top.macondo.java.juc.threads.evendriven.eda;

/**
 * @author: zhangchong
 * @Date: 2020/8/8 22:29
 **/
public interface Channel<E extends Message> {
	void dispatch(E message);
}
