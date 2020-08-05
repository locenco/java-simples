package top.macondo.java.netty.protocol.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: zhangchong
 * @Date: 2020/8/4 15:14
 **/
@Slf4j
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	private final ChannelGroup group;

	public TextWebSocketFrameHandler(ChannelGroup group) {
		this.group = group;
	}

	/*@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){
			group.writeAndFlush(new TextWebSocketFrame("Client" + ctx.channel() + " joined"));
			group.add(ctx.channel());
		}else {
			super.userEventTriggered(ctx,evt);
		}
	}*/

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
		group.writeAndFlush(new TextWebSocketFrame("服务器收到消息: " + textWebSocketFrame.text()));
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		group.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		log.info("客户端断开连接：",ctx.channel().id().asLongText());
	}
}
