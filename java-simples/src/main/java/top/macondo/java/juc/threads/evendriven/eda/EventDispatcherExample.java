package top.macondo.java.juc.threads.evendriven.eda;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.transform.Result;

/**
 * @author: zhangchong
 * @Date: 2020/8/8 22:40
 **/
public class EventDispatcherExample {
	@AllArgsConstructor
	@Data
	public static class InputEvent extends Event {
		private final int x;
		private final int y;
	}
	@AllArgsConstructor
	@Data
	public static class ResultEvent extends Event{
		private final int result;
	}
	static class ResultEventHandler implements Channel<ResultEvent>{

		@Override
		public void dispatch(ResultEvent message) {
			System.out.println("The result is:" + message.getResult());
		}
	}
	static class InputEventHandler implements Channel<InputEvent>{
		private final EventDispatcher eventDispatcher;

		InputEventHandler(EventDispatcher eventDispatcher) {
			this.eventDispatcher = eventDispatcher;
		}

		@Override
		public void dispatch(InputEvent message) {
			System.out.printf("X: %d, Y: %d\n",message.getX(),message.getY());
			int result = message.getX() + message.getY();
			eventDispatcher.dispatch(new ResultEvent(result));
		}
	}

	public static void main(String[] args) {
		EventDispatcher dispatcher = new EventDispatcher();
		dispatcher.registerChannel(InputEvent.class,new InputEventHandler(dispatcher));
		dispatcher.registerChannel(ResultEvent.class,new ResultEventHandler());
		dispatcher.dispatch(new InputEvent(1,2));
	}

}
