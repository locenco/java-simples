package top.macondo.java.juc.threads.evendriven.eda;

import java.util.Hashtable;
import java.util.Map;

/**
 * 事件调度器
 * 主要包含： 路由表、调度路由
 * 1. 路由表存储消息和管道的kv。
 * 2. 调度路由输入为消息，先找到对应管道，再进行处理和进一步调度
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
