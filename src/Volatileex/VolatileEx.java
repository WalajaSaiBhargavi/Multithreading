package Volatileex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class VolatileEx {
    public static void main(String [] args) {
        Runner r1 = new Runner();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);

        t1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        r1.shutdown();
    }
}
class Runner implements Runnable{
     private  boolean running = true;
    private  int i = 0;
    @Override
    public void run() {
        while (running) {
            System.out.println("thread"+i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            i = i+1;
        }
    }
    public void shutdown(){
        running = false;
    }
}
