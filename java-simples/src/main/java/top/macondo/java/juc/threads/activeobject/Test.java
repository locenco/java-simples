package top.macondo.java.juc.threads.activeobject;

import top.macondo.java.juc.threads.activeobject.message.ActiveObject;

public class Test {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        new MakerClientThread(activeObject, "Alex").start();
        new MakerClientThread(activeObject, "Bobby").start();

        new DisplayClientThread("Chris", activeObject).start();
    }
}