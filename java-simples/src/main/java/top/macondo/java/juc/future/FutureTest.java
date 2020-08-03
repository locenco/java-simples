package top.macondo.java.juc.future;

import org.junit.Test;

import java.util.concurrent.*;

/**
 *
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println("start");
        Future<Integer> submit = executorService.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        Integer value = null;
        try {
            value = submit.get();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        System.out.println(value);
        System.out.println("end");
        
    }

    /**
     * executorService.submit()方法获取带返回值的 Future 结果有两种方式：
     *
     * 一种是通过实现 Callable接口；
     * 第二种是中间变量返回。继承 Future 的子类: FutureTask，通过 FutureTask 返回异步结果而不是在主线程中获取（FutureTask 本质也是使用 Callable 进行创建）。
     */
    @Test
    public void testFutureTask() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println("start");
        //方式1 通过 executorService 提交一个异步线程
        //Future<Integer> submit = executorService.submit(new NewCallableTask());

        //方式2 通过 FutureTask 包装异步线程的返回，返回结果在 FutureTask 中获取而不是 在提交线程中
        FutureTask<Integer> task = new FutureTask<>(new NewCallableTask());
        executorService.submit(task);
        //-------------方式2--------------

        Integer value = null;
        try {
            value = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(value);
        System.out.println("end");

    }

    /**
     * 通过实现 Callable 接口
     */
    static class NewCallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }
    }

}