package top.macondo.netty.rpccore.client;

import top.macondo.netty.rpccore.pojo.RpcRequest;
import top.macondo.netty.rpccore.pojo.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 17:22
 **/
public class RpcProxy {
	private String serverAddress;
	private ServiceDiscovery serviceDiscovery;

	public RpcProxy(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public RpcProxy(ServiceDiscovery serviceDiscovery) {
		this.serviceDiscovery = serviceDiscovery;
	}
	public <T> T create(Class<?> interfaceClass){
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
				new Class<?>[]{interfaceClass},
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						RpcRequest rpcRequest = new RpcRequest();
						rpcRequest.setRequestId(UUID.randomUUID().toString());
						rpcRequest.setClassName(method.getDeclaringClass().getName());
						rpcRequest.setMethodName(method.getName());
						rpcRequest.setParameterTypes(method.getParameterTypes());
						rpcRequest.setParameters(args);
						if(serviceDiscovery != null){
							serverAddress = serviceDiscovery.discover();
						}

						String[] addressArray = serverAddress.split(":");
						String host = addressArray[0];
						int port = Integer.parseInt(addressArray[1]);
						RpcClient client = new RpcClient(host, port);
						RpcResponse response = client.send(rpcRequest);
						if(response.isError()){
							return response.getError();
						}else {
							return response.getResult();
						}
					}
				});
	}
}
