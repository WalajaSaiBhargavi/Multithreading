package Syncrhonizedex;

public class app2 {
    private int count = 0;
    public static void main(String[] args){
        app2 app2 = new app2();
        app2.dowork();
    }
    public void dowork(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }

    private synchronized int increment() {
        for (int i = 0; i < 10000; i++) {
             count++;
        }
        return count;
    }
}
