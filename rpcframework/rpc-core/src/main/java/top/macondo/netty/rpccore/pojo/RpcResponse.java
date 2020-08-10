package top.macondo.netty.rpccore.pojo;

import lombok.Data;

@Data
public class RpcResponse {

    private String requestId;
    private Throwable error;
    private Object result;

    public boolean isError() {
        return error == null ? false : true;
    }

    // getter/setter...
}