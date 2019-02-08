package CallableAndFuture;

import java.util.Random;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args){
        ExecutorService ex = Executors.newCachedThreadPool();
        Future<Integer> future = ex.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Thread started..");
                Random r = new Random();
                int duration = r.nextInt(4000);
                Thread.sleep(duration);
                System.out.println("Callable thread execution stopped");
                return duration;
            }
        });
        Future<?> future1 = ex.submit(new Callable<Void>() {
            @Override
            public Void call(){
                System.out.println("When we do not want to return anything from callable this is the format");
                return null;
            }
        });
        ex.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am in runnable method");
            }
        });

        ex.shutdown();
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
