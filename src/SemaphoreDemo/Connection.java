package SemaphoreDemo;

import java.util.concurrent.Semaphore;

public class Connection {
    private static Connection instance = new Connection();
    private static int count = 0;
    public static Semaphore sem = new Semaphore(10);
    private Connection(){

    }
    public static Connection connect() throws InterruptedException {
        sem.acquire();
        instance.restrictConnection();
        return instance;
    }

    private void restrictConnection() throws InterruptedException {
        synchronized (this){
            count++;
        }
        Thread.sleep(2000);
        System.out.println("number of connections...  "+ count +"  Only  "+ sem.availablePermits() +" more connections can be made");
        sem.release();
        synchronized (this){
            count--;
        }
    }
}
