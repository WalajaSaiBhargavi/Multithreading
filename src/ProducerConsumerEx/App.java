package ProducerConsumerEx;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App extends Thread{
    static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    public static void main(String [] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
    public static void producer() throws InterruptedException {
        Random r = new Random();
        while (true){
            try {
                Thread.sleep(500);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            int k = r.nextInt(10);
             queue.put(k);
            System.out.println("Adding element "+ k);
            System.out.println("the size of the queue is ..."+ queue.size());
        }
    }
    public static void consumer() throws InterruptedException {
        Random r1 = new Random();
        while(true){
            try {
            Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(r1.nextInt(10) == 0){
                System.out.println("Removing queue element "+ queue.take());
            }
        }
    }
}
