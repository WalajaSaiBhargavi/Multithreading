package SemaphoreDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args){
        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i = 0; i < 200; i++) {
            ex.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection.connect();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
