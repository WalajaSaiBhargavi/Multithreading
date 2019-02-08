package MultiThreadEx;

class Runner implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++) {
            System.out.println("print "+ i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hello();
    }
    public void hello(){
    System.out .println("helloooooo");
    }
}

public class Demo {
    public static void main(String [] args){
        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());
        t1.start();
        t2.start();

    }
}
