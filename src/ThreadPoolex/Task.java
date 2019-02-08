package ThreadPoolex;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task implements Runnable {
    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {


            System.out.println("Starting task..." + id);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("completed task" + id);


    }


}
 class Webtask implements Runnable {
    private int id;
    public Webtask(int id) {
        this.id = id;
    }
    @Override
    public void run() {

            System.out.println("Started webtask..." + id);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("completed webtask" + id);

    }
}
 class MainClass {
    public static void main(String [] args){
        Runnable r1 = new Task(1);
        Runnable r2 = new Task(2);
        Runnable r3 = new Task(3);
        Runnable r4 = new Webtask(1);
        Runnable r5 = new Webtask(2);
        Runnable r6 = new Webtask(3);
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(r1);
        pool.submit(r2);
        pool.submit(r3);
        pool.submit(r4);
        pool.submit(r5);
        pool.submit(r6);
        pool.shutdown();
    }
 }

