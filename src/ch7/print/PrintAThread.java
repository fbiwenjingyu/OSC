package ch7.print;

/**
 * Created by Administrator on 2019/3/10.
 */
public class PrintAThread implements  Runnable{
    PrintJob printJob;

    public PrintAThread(PrintJob printJob) {
        this.printJob = printJob;
    }
    public void run() {
        try {
            while (true){
                printJob.printA();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
