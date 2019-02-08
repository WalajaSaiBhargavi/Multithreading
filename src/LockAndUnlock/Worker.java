package LockAndUnlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Worker {
    private int count = 0;
    Lock lock = new ReentrantLock();
    Condition cond = lock.newCondition();
    private void increment(){
        for (int i = 0; i < 10000 ; i++) {
            count++;
        }
    }
    public void firstThread() throws InterruptedException {
        lock.lock();// best to put the method for which we lock in try catch block
        System.out.println("Got the lock to FirstThread.... executing FirstThread");
        cond.await();
        System.out.println(" FirstThread.... Woke up!!");
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }
    public void secondThread() throws InterruptedException {
        Thread.sleep(2000);
        lock.lock();
        System.out.println("Got the lock to SecondThread.... executing SecondThread");
        cond.signal();
        try {
            System.out.println("This will execute till unlock....");
            increment();
        } finally {
            lock.unlock();
        }
        Thread.sleep(1000);
        System.out.println("This will execute last");
    }

    public void alLast() {
        System.out.println(count);
    }
}
