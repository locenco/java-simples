package top.macondo.java.juc.threads.activeobject;
/**
 *    对应ActiveObjects的每一个方法,将每个方法转换成一个对象
 *    表示请求的抽象类
 */
public abstract class MethodRequest {
    protected final Servant servant;
    
    protected final FutureResult futureresult;
    
    public MethodRequest(Servant servant, FutureResult futureresult) {
        this.servant = servant;
        this.futureresult = futureresult;
    }

    public abstract void execute();
}