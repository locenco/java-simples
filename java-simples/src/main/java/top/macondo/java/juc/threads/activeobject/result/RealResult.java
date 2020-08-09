package top.macondo.java.juc.threads.activeobject.result;

public class RealResult implements Result {
    private final Object resultValue;
    
    public RealResult(Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public Object getResultValue() {
        return this.resultValue;
    }

}