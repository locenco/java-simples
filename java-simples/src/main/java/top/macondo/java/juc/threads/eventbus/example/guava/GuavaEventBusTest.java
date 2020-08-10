package top.macondo.java.juc.threads.eventbus.example.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 11:46
 **/
public class GuavaEventBusTest {
	class EventListener{
		public int LastMessage = 0;
		@Subscribe
		public void listen(TestEvent event){
			this.LastMessage = event.getMessage();
		}
		public int getLastMessage(){
			return this.LastMessage;
		}
	}
	class TestEvent{
		private final int message;

		TestEvent(int message) {
			this.message = message;
		}
		public int getMessage(){
			return this.message;
		}
	}
	@Test
	public void testReceiveEvent(){
		EventBus eventBus = new EventBus("test");
		EventListener eventListener = new EventListener();
		eventBus.register(eventListener);

		eventBus.post(new TestEvent(1));
		Assert.assertTrue(eventListener.getLastMessage() == 1);
	}
}
