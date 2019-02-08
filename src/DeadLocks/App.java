package DeadLocks;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Process p = new Process();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                p.firstThread();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        p.atLast();
    }
}
