package top.macondo.java.juc.threads.activeobject;
/**
 *    {@link ActiveObject#makeString(int, char)}
 */
public class MakeStringRequest extends MethodRequest {
    private final int count;
    private final char fillChar;
    
    public MakeStringRequest(Servant servant, FutureResult futureresult, int count, char fillChar) {
        super(servant, futureresult);
        this.count = count;
        this.fillChar = fillChar;
    }

    @Override
    public void execute() {
        Result result = servant.makeString(count, fillChar);
        futureresult.setResult(result);
    }
    
}