package top.macondo.netty.rpccore.client;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.macondo.netty.rpccore.server.HelloService;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 15:12
 **/
@SpringBootApplication
public class RpcClientBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(RpcClientBootstrap.class, args);

	}
}
