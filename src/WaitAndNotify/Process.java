package WaitAndNotify;


public class Process {
    public static void main(String[] args) throws InterruptedException {
        Worker w = new Worker();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    w.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    w.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();

    }
}

class Worker{
    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Started the prodicer");
            wait();
            System.out.println("resumed");
        }
    }
    public void consume() throws InterruptedException {
        Thread.sleep(10000);
        synchronized (this){
            System.out.println("Started consumer");
            notify();
            System.out.println("This will be executed next");
        }
    }
}
