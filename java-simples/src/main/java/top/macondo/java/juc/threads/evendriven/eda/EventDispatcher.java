package top.macondo.java.juc.threads.evendriven.eda;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author: zhangchong
 * @Date: 2020/8/8 22:34
 **/
public class EventDispatcher implements DynamicRouter<Message> {
	private final Map<Class<? extends Message>, Channel> routerTable;

	public EventDispatcher() {
		this.routerTable = new Hashtable<>();
	}

	@Override
	public void registerChannel(Class messageType, Channel channel) {
		this.routerTable.put(messageType,channel);
	}

	@Override
	public void dispatch(Message message) {
		if (routerTable.containsKey(message.getType())){
			routerTable.get(message.getType()).dispatch(message);
		}else {
			throw new MessageMatcherException("can't match the channel for ["+message.getType()+"] type");
		}
	}
}
