package top.macondo.netty.rpccore.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import top.macondo.netty.rpccore.handler.RpcDecoder;
import top.macondo.netty.rpccore.handler.RpcEncoder;
import top.macondo.netty.rpccore.handler.RpcHandler;
import top.macondo.netty.rpccore.pojo.RpcRequest;
import top.macondo.netty.rpccore.pojo.RpcResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 15:28
 **/
@Slf4j
public class RpcServer implements ApplicationContextAware, InitializingBean {
	private final String serverAddress;
	private ServiceRegistry serviceRegistry;

	private Map<String, Object> handlerMap = new HashMap<>();

	public RpcServer(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	public RpcServer(String serverAddress, ServiceRegistry serviceRegistry) {
		this.serverAddress = serverAddress;
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
		Optional.ofNullable(serviceBeanMap).orElse(new HashMap<>())
				.values().forEach(serverBean -> {
					String interfaceName = serverBean.getClass().getAnnotation(RpcService.class).value().getName();
					handlerMap.put(interfaceName,serverBean);
		});
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup,workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline()
									.addLast(new RpcDecoder(RpcRequest.class))
									.addLast(new RpcEncoder(RpcResponse.class))
									.addLast(new RpcHandler(handlerMap));
						}
					})
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);

			String[] addressArray = serverAddress.split(":");
			String host = addressArray[0];
			int port = Integer.parseInt(addressArray[1]);

			ChannelFuture future = bootstrap.bind(host, port).sync();
			log.debug("server started on port {}", port);

			if(serviceRegistry != null){
				serviceRegistry.register(serverAddress);
			}
			future.channel().closeFuture().sync();
		}finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}
