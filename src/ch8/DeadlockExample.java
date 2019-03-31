package ch8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {

	public static void main(String[] args) {
		Lock lockX = new ReentrantLock();
		Lock lockY = new ReentrantLock();
		
		Thread threadA = new Thread(new A(lockX,lockY));
		Thread threadB = new Thread(new B(lockX,lockY));
		
		threadA.start();
		threadB.start();
	}

}

class A implements Runnable{
	private Lock first;
	private Lock second;
	
	public A(Lock first, Lock second) {
		this.first = first;
		this.second = second;
	}
	


	@Override
	public void run() {
		try {
			first.lock();
			System.out.println("ThreadA got first lock");
			try {
				Thread.sleep( ((int) (3 * Math.random()))* 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			second.lock();
			System.out.println("ThreadA got second lock");
		}finally {
			first.unlock();
			second.unlock();
		}
	}
	
}


class B implements Runnable{
	private Lock first;
	private Lock second;
	
	public B(Lock first,Lock second) {
		this.first = first;
		this.second = second;
	}
	


	@Override
	public void run() {
		try {
			second.lock();
			System.out.println("ThreadB got second lock");
			try {
				Thread.sleep( ((int) (3 * Math.random()))* 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			first.lock();
			System.out.println("ThreadB got first lock");
		}finally {
			second.unlock();
			first.unlock();
			
		}
	}
	
}

