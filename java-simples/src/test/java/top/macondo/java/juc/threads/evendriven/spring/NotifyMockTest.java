package top.macondo.java.juc.threads.evendriven.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.mock;

/**
 * @author: zhangchong
 * @Date: 2020/8/12 14:46
 **/
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class NotifyMockTest  {
	NotifyPublisher notifyPublisher;
	private ApplicationContext ctx;

	private ApplicationEventPublisher applicationEventPublisher;

	@Before
	public void setup() throws Exception {
		ctx = mock(ApplicationContext.class);
		applicationEventPublisher = mock(ApplicationEventPublisher.class);
		notifyPublisher = new NotifyPublisher(applicationEventPublisher);
		notifyPublisher.setApplicationContext(ctx);
	}
	@Test
	public void testNotify(){
		notifyPublisher.publishEvent(1,"test");
	}
}