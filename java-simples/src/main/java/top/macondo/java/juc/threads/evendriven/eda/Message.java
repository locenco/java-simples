package top.macondo.java.juc.threads.evendriven.eda;

/**
 * @author: zhangchong
 * @Date: 2020/8/8 22:28
 **/
public interface Message {
	Class<? extends Message> getType();
}
