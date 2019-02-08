package CountDownLatchEx;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Worker implements Runnable {
    CountDownLatch latch;
    int id;
    public Worker(CountDownLatch latch, int id){
        this.latch = latch;
        this.id = id;
    }
    @Override
    public void run() {
        System.out.println("started.."+id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed..."+id);
        System.out.println("Main thread exiting"+Thread.currentThread().getName());
        latch.countDown();
    }
}
class App{
    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            pool.submit(new Worker(latch, i));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        System.out.println("Main thread exiting"+Thread.currentThread().getName());
    }
}