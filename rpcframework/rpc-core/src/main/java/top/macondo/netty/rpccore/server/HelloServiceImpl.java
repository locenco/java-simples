package top.macondo.netty.rpccore.server;

import top.macondo.netty.rpccore.RpcService;

@RpcService(HelloService.class) // 指定远程接口
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }
}