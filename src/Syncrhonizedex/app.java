package Syncrhonizedex;

//Demonstrates example without syncronised

public class app {
    private int count = 0;
    public static void main(String args[]){
        app app = new app();

    app.dowork();
    }
    public void dowork(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000 ; i++) {
                    count++;
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000 ; i++) {
                    count++;
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();// join keywork would wait for till the thread return from execution helps to wait before functions which need info from the thread executing
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count"+count);
    }



}
