package top.macondo.java.netty.protocol.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.ImmediateEventExecutor;
import org.omg.SendingContext.RunTime;

import java.net.InetSocketAddress;

/**
 * @author: zhangchong
 * @Date: 2020/8/4 11:44
 **/
public class WebSocketServer {
	private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup group = new NioEventLoopGroup();
	private Channel channel;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Please give port as argument");
			System.exit(1);
		}
		int port = Integer.parseInt(args[0]);

		final WebSocketServer endpoint = new WebSocketServer();
		ChannelFuture future = endpoint.start(new InetSocketAddress(port));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> endpoint.destory()));
		future.channel().closeFuture().syncUninterruptibly();
	}

	private void destory() {
		if (channel != null){
			channel.close();
		}
		channelGroup.close();
		group.shutdownGracefully();
	}

	/**
	 *
	 * @param address
	 * @return
	 */
	public ChannelFuture start(InetSocketAddress address){
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(group)
				.channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(createInitializer(channelGroup));
		ChannelFuture future = serverBootstrap.bind(address);
		future.syncUninterruptibly();
		channel = future.channel();
		return future;
	}
	protected ChannelInitializer<Channel> createInitializer(ChannelGroup group){
		return new WebSocketServerIntializer(group);
	}

}
