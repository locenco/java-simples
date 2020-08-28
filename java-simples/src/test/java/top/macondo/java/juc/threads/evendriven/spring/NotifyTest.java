package top.macondo.java.juc.threads.evendriven.spring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.macondo.java.JavaSimplesApplicationTests;

import static org.junit.Assert.*;

/**
 * @author: zhangchong
 * @Date: 2020/8/12 14:46
 **/
public class NotifyTest extends JavaSimplesApplicationTests {
	@Autowired
	NotifyPublisher notifyPublisher;
	@Test
	public void testNotify(){
		notifyPublisher.publishEvent(1,"test");
	}
}