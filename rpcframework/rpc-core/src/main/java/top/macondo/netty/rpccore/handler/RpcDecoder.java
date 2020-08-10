package top.macondo.netty.rpccore.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import top.macondo.netty.rpccore.pojo.RpcRequest;
import top.macondo.netty.rpccore.SerializationUtil;

import java.util.List;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 15:52
 **/
public class RpcDecoder extends ByteToMessageDecoder{
	private Class<?> genericClass;

	public RpcDecoder(Class<?> genericClass) {
		this.genericClass = genericClass;
	}

	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
		if(byteBuf.readableBytes() < 4){
			return;
		}
		byteBuf.markReaderIndex();
		int dataLength = byteBuf.readInt();
		if(dataLength < 0){
			channelHandlerContext.close();
		}
		if(byteBuf.readableBytes() < dataLength){
			byteBuf.resetReaderIndex();
			return;
		}
		byte[] data = new byte[dataLength];
		byteBuf.readBytes(data);
		Object obj = SerializationUtil.deserialize(data, genericClass);
		list.add(obj);
	}
}
