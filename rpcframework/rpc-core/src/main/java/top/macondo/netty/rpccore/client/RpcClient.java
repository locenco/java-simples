package top.macondo.netty.rpccore.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import top.macondo.netty.rpccore.handler.RpcDecoder;
import top.macondo.netty.rpccore.handler.RpcEncoder;
import top.macondo.netty.rpccore.pojo.RpcRequest;
import top.macondo.netty.rpccore.pojo.RpcResponse;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 17:28
 **/
@Slf4j
public class RpcClient extends SimpleChannelInboundHandler<RpcResponse>{

	private final String host;
	private final int port;

	private RpcResponse rpcResponse;
	private final Object MUTEX = new Object();

	public RpcClient(String host, int port) {
		this.host = host;
		this.port = port;
	}


	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
		this.rpcResponse = msg;
		synchronized (MUTEX){
			MUTEX.notifyAll();
		}
	}
	public RpcResponse send(RpcRequest request) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
					.channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline()
									.addLast(new RpcEncoder(RpcRequest.class))
									.addLast(new RpcDecoder(RpcResponse.class))
									.addLast(RpcClient.this);
						}
					})
					.option(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().writeAndFlush(request).sync();

			synchronized (MUTEX){
				MUTEX.wait();
			}
			if(rpcResponse != null){
				future.channel().closeFuture().sync();
			}
			return rpcResponse;
		} finally {
			group.shutdownGracefully();
		}
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error("client caught exception",cause);
		ctx.close();
	}
}
