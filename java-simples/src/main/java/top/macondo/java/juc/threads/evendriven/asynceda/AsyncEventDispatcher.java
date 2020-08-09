package top.macondo.java.juc.threads.evendriven.asynceda;

import top.macondo.java.juc.threads.evendriven.eda.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zhangchong
 * @Date: 2020/8/8 22:58
 **/
public class AsyncEventDispatcher implements DynamicRouter<Event> {
	private final Map<Class<? extends Message>, AsyncChannel> routerTable;

	public AsyncEventDispatcher() {
		routerTable = new ConcurrentHashMap<>();
	}

	@Override
	public void registerChannel(Class<? extends Event> messageType, Channel<? extends Event> channel) {
		if(!(channel instanceof AsyncChannel)){
			throw new IllegalArgumentException("The channel must be AsyncChannel Type.");
		}
		this.routerTable.put(messageType,(AsyncChannel)channel);
	}

	@Override
	public void dispatch(Event message) {
		if(routerTable.containsKey(message.getType())){
			routerTable.get(message.getType()).dispatch(message);
		}else {
			throw new MessageMatcherException("can't match the channel for ["+message.getType()+"] type");
		}
	}
	public void shutdown(){
		routerTable.values().forEach(AsyncChannel::stop);
	}
}
