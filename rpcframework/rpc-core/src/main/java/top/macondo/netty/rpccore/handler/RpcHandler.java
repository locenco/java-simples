package top.macondo.netty.rpccore.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import top.macondo.netty.rpccore.pojo.RpcRequest;
import top.macondo.netty.rpccore.pojo.RpcResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 16:26
 **/
@Slf4j
public class RpcHandler extends SimpleChannelInboundHandler<RpcRequest> {

	private final Map<String, Object> handlerMap;

	public RpcHandler(Map<String, Object> handlerMap) {
		this.handlerMap = handlerMap;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
		RpcResponse response = new RpcResponse();
		response.setRequestId(msg.getRequestId());
		try {
			Object result = handle(msg);
			response.setResult(result);
		}catch (Throwable t){
			response.setError(t);
		}
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
	private Object handle(RpcRequest request) throws InvocationTargetException {
		String className = request.getClassName();
		Object serviceBean = handlerMap.get(className);
		Class<?> serviceClass = serviceBean.getClass();
		String methodName = request.getMethodName();
		Class<?>[] paramterTypes = request.getParameterTypes();
		Object[] parameters = request.getParameters();

		FastClass serviceFastClass = FastClass.create(serviceClass);
		FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, paramterTypes);
		return serviceFastMethod.invoke(serviceBean,parameters);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error("server caught exception",cause);
		ctx.close();
	}
}
