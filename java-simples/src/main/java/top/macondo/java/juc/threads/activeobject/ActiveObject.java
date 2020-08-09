package top.macondo.java.juc.threads.activeobject;
/**
 *    接受异步消息的主动对象,类似 System.gc();
 */
public interface ActiveObject {
    Result makeString(int count, char fillChar);
    
    void displayString(String text);
}