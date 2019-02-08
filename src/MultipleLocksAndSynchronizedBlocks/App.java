package MultipleLocksAndSynchronizedBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String args[]){
        Worker w = new Worker();
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                w.process();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                w.process();
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
        long end = System.currentTimeMillis();
        System.out.println("time ="+(end - start) + "  "+ end +"   "+start);
        System.out.println(w.list1.size());
        System.out.println(w.list2.size());
    }
}
class Worker{
    Object lock1 = new Object();
    Object lock2 = new Object();
    public List<Integer> list1 = new ArrayList<>();
    public List<Integer> list2 = new ArrayList<>();
    Random r = new Random();
    public  void stage1(){
        synchronized (lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(r.nextInt());
        }
    }
    public  void stage2(){
        synchronized (lock2){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(r.nextInt());
        }
    }
    public void process(){
        for (int i = 0; i < 500; i++) {
            stage1();
            stage2();
        }
    }
}
