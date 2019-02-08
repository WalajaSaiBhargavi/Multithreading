package LowLevelWaitAndNotify;

public class App {
    public static void main(String [] args){
        Worker w = new Worker();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    w.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    w.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
