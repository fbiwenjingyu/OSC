package ch7.print;

/**
 * Created by Administrator on 2019/3/10.
 */
public class Client {
    public static void main(String[] args) {
        PrintJob job = new PrintJob();
        Thread a = new Thread(new PrintAThread(job));
        Thread b = new Thread(new PrintBThread(job));
        a.start();
        b.start();
    }
}
