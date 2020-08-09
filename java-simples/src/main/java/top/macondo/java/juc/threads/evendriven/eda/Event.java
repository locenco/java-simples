package top.macondo.java.juc.threads.evendriven.eda;

/**
 * @author: zhangchong
 * @Date: 2020/8/8 22:33
 **/
public class Event implements Message{
	@Override
	public Class<? extends Message> getType() {
		return getClass();
	}
}
