package ch7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RRPrinter {
	static Lock lock = new ReentrantLock();
	static Condition[] conVars  = new Condition[5];
	static int turn = 0;
	public static void main(String[] args) {
		
		
		for(int i=0;i<5;i++) {
			conVars[i]  = lock.newCondition();
		}
		
		PrintWork printA = new PrintWork(0, "A");
		PrintWork printB = new PrintWork(1, "B");
		PrintWork printC = new PrintWork(2, "C");
		PrintWork printD = new PrintWork(3, "D");
		PrintWork printE = new PrintWork(4, "E");
		
		Thread threadA = new Thread(printA);
		Thread threadB = new Thread(printB);
		Thread threadC = new Thread(printC);
		Thread threadD = new Thread(printD);
		Thread threadE = new Thread(printE);
		
		threadA.start();
		threadB.start();
		threadC.start();
		threadD.start();
		threadE.start();
	}
	
	public static void doWork(int threadNumber,String str) {
		lock.lock();
		try {
			while(threadNumber != turn) {
				conVars[threadNumber].await();
			}
			System.out.println("I am printing " + str);
			Thread.sleep(1000);
			turn = (turn + 1) % 5;
			conVars[turn].signal();
		}catch (InterruptedException e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
	
	static class PrintWork implements Runnable{
		private int threadNumber;
		private String str;
		
		public PrintWork(int threadNumber, String str) {
			this.threadNumber = threadNumber;
			this.str = str;
		}


		@Override
		public void run() {
			for(int i=0;i<10;i++) {
				doWork(threadNumber,str);
			}
		}
		
	}

}
