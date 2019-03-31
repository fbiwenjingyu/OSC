package ch7.print;

/**
 * Created by Administrator on 2019/3/10.
 */
public class PrintJob {
    private boolean isTurnA = true;
    private boolean isTurnB = false;
    public synchronized void printA() throws InterruptedException {
       while (!isTurnA){
           wait();
       }
       System.out.println("Now is A");
       isTurnA = false;
       isTurnB = true;
       notify();
     }

    public synchronized void printB() throws InterruptedException{
        while (!isTurnB){
            wait();
        }
        System.out.println("Now is B");
        isTurnA = true;
        isTurnB = false;
        notify();
    }

}
