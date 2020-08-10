package top.macondo.netty.rpccore.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 18:01
 **/
@Configuration
public class ClientConfig{

	@Value("${registry.address}")
	private String registryAddress;

	@Bean
	public ServiceDiscovery serviceDiscovery(){
		return new ServiceDiscovery(registryAddress);
	}
	@Bean
	public RpcProxy rpcProxy(ServiceDiscovery serviceDiscovery){
		return new RpcProxy(serviceDiscovery);
	}
}
