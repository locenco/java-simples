package top.macondo.java.juc.threads.eventbus;



/**
 * @author: zhangchong
 * @Date: 2020/8/9 9:02
 **/
public interface EventExceptionHandler {
	void handle(Throwable cause, EventContext context);
}
