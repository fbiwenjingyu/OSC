package ch7.print;

/**
 * Created by Administrator on 2019/3/10.
 */
public class PrintBThread implements  Runnable{
    PrintJob printJob;

    public PrintBThread(PrintJob printJob) {
        this.printJob = printJob;
    }
    public void run() {

        try {
            while (true){
                printJob.printB();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
