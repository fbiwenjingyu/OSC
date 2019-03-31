package ch7.print;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintSemaphore {
	private static int num = 0;
	private static final  int N = 5;
	private static int turn = 0;
	private static Semaphore[] semaphore = new Semaphore[N];
	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<N;i++) {
			semaphore[i] = new Semaphore(1);
		}
		
		for(int i=0;i<N;i++) {
			semaphore[i].acquire();
		}
		 
		Thread[] threads = new Thread[N];
		for(int i=0;i<N;i++) {
			threads[i] = new Thread(new PrintThread(i));
		}
		
		for(int i=0;i<N;i++) {
			threads[i].start();
		}
	}
	
	private static void doPrintJob(int threadNum) {
		
		try {
			while(turn != threadNum) {
				semaphore[threadNum].acquire();
			}
			System.out.println("Thread " + threadNum + "printing " + num++);
			if(num > 100) {
				System.exit(0);
			}
			turn = (turn + 1) % N;
			semaphore[turn].release();
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			
		}
		
	}
	
	private static class PrintThread implements Runnable{
		private int threadNum;
		
		public PrintThread(int threadNum) {
			this.threadNum = threadNum;
		}

		@Override
		public void run() {
			while(true) {
				doPrintJob(threadNum);
			}
			
		}
		
	}
	

}
