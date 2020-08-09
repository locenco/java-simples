package top.macondo.java.juc.threads.activeobject.message;

import top.macondo.java.juc.threads.activeobject.message.MethodRequest;
import top.macondo.java.juc.threads.activeobject.message.Servant;

public class DisplayStringRequest extends MethodRequest {
    private final String text;

    public DisplayStringRequest(Servant servant, final String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        this.servant.displayString(text);
    }

}