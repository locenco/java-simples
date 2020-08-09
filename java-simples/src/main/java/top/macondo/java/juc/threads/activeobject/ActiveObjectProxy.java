package top.macondo.java.juc.threads.activeobject;

import top.macondo.java.juc.threads.activeobject.message.ActiveObject;
import top.macondo.java.juc.threads.activeobject.message.DisplayStringRequest;
import top.macondo.java.juc.threads.activeobject.message.MakeStringRequest;
import top.macondo.java.juc.threads.activeobject.message.Servant;
import top.macondo.java.juc.threads.activeobject.result.FutureResult;
import top.macondo.java.juc.threads.activeobject.result.Result;

class ActiveObjectProxy implements ActiveObject {
    private final SchedulerThread schedulerThread;
    
    private final Servant servant;
    
    public ActiveObjectProxy(SchedulerThread schedulerThread, Servant servant) {
        this.schedulerThread = schedulerThread;
        this.servant = servant;
    }

    @Override
    public Result makeString(int count, char fillChar) {
        FutureResult future = new FutureResult();
        schedulerThread.invoke(new MakeStringRequest(servant, future, count, fillChar));
        return future;
    }

    @Override
    public void displayString(String text) {
        schedulerThread.invoke(new DisplayStringRequest(servant, text));
    }
    
}