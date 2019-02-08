package DeadLocks;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Process {
    Account acc1 = new Account();
    Account acc2 = new Account();
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();
    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while(true){
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;
            try{
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            }finally {
                if(gotFirstLock && gotSecondLock)
                    return;
                Thread.sleep(1);
                if(gotFirstLock)
                    firstLock.unlock();
                if(gotSecondLock)
                    secondLock.unlock();
            }
        }
    }
    public void firstThread(){
        Random random = new Random();
        for (int i = 0; i <100 ; i++) {
            try {
                acquireLocks(lock1, lock2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                acc1.transfer(acc2, random.nextInt(100));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
    public void secondThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i <100 ; i++) {
            acquireLocks(lock2, lock1);
            try {
                acc2.transfer(acc1, random.nextInt(100));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
    public void atLast(){
        System.out.println(acc1.getBalance());
        System.out.println(acc2.getBalance());
        System.out.println("total balance "+ (acc1.getBalance()+acc2.getBalance()));
    }
}
