package top.macondo.netty.rpccore.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 17:56
 **/
@Configuration
public class ServerConfig {
	@Value("${registry.address}")
	private String registryAddress;

	@Value("${server.address}")
	private String serverAddress;

	@Bean
	public ServiceRegistry serviceRegistry(){
		return new ServiceRegistry(registryAddress);
	}
	@Bean
	public RpcServer rpcServer(ServiceRegistry serviceRegistry){
		return new RpcServer(serverAddress, serviceRegistry);
	}

}
