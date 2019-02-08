package LowLevelWaitAndNotify;

import java.util.LinkedList;

public class Worker {
    LinkedList<Integer> list = new LinkedList<>();
    private final int MAX_VALUE = 10;
    Object lock = new Object();
    public void producer() throws InterruptedException {
        int value = 0;
        while (true){
            synchronized (lock){
                while (list.size() == MAX_VALUE){
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }
    public void consumer() throws InterruptedException {
        while(true){
            synchronized (lock){
                while(list.size() == 0){
                    lock.wait();
                }
                int value = list.removeFirst();
                Thread.sleep(2000);
                System.out.println("Removed value..." + value);
                lock.notify();
            }
        }
    }
}
