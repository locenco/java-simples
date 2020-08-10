package top.macondo.netty.rpccore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import top.macondo.netty.rpccore.client.RpcClientBootstrap;
import top.macondo.netty.rpccore.client.RpcProxy;
import top.macondo.netty.rpccore.client.ClientConfig;
import top.macondo.netty.rpccore.server.HelloService;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 17:52
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RpcClientBootstrap.class)
public class HelloServiceTest{
	@Autowired
	private RpcProxy rpcProxy;
	@Test
	public void testHello(){
		HelloService helloService = rpcProxy.create(HelloService.class);
		String result = helloService.hello("World");
		System.out.println("result = " + result);
		Assert.assertEquals("Hello! World", result);
	}
}
