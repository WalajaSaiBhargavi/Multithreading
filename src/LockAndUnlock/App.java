package LockAndUnlock;


public class App {
    public static void main(String [] args) throws InterruptedException {
        Worker w = new Worker();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    w.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    w.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        w.alLast();
    }
}
