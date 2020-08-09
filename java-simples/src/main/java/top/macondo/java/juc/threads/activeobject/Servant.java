package top.macondo.java.juc.threads.activeobject;

/**
 * Servant 定义了 Active Object 的行为和状态，它是 Proxy 所定义的接口的事实实现。
 * 执行实际处理的类（实现了ActiveObject接口）
 */
class Servant implements ActiveObject {

    @Override
    public void displayString(String text) {
        try {
            System.out.println("Display:" + text);
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Result makeString(int count, char fillChar) {
        char[] buf = new char[count];
        for(int i = 0; i < count; i++) {
            buf[i] = fillChar;
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
        return new RealResult(new String(buf));
    }

}