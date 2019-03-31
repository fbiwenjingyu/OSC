package ch7.print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintCondition {
	private static int num = 0;
	private static final  int N = 5;
	private static int turn = 0;
	private static Lock lock = new ReentrantLock();
	private static Condition[] conditions = new Condition[N];
	public static void main(String[] args) {
		for(int i=0;i<N;i++) {
			conditions[i] = lock.newCondition();
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
		lock.lock();
		try {
			while(turn != threadNum) {
				conditions[threadNum].await();
			}
			System.out.println("Thread " + threadNum + "printing " + num++);
			if(num > 100) {
				System.exit(0);
			}
			turn = (turn + 1) % N;
			conditions[turn].signalAll();
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			lock.unlock();
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
