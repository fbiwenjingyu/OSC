package ch7.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

	public static void main(String[] args) {
//		ThreadPool pool = new ThreadPool();
//		pool.add(new Task(5,10));
//		pool.add(new Task(1,2));
//		pool.add(new Task(5,1));
//		pool.add(new Task(15,0));
//		pool.add(new Task(75,7));
//		pool.add(new Task(12,-10));
		
		
//		pool.addExtra(new Task(5,10));
//		pool.addExtra(new Task(1,2));
//		pool.addExtra(new Task(5,1));
//		pool.addExtra(new Task(15,0));
//		pool.addExtra(new Task(75,7));
//		pool.addExtra(new Task(12,-10));
//		
//		pool.shutdown();
//		
		
		
		
		ExecutorService pool = Executors.newFixedThreadPool(5);
		pool.execute(new Task(5,10));
		pool.execute(new Task(1,2));
		pool.execute(new Task(5,1));
		pool.execute(new Task(15,0));
		pool.execute(new Task(75,7));
		pool.execute(new Task(12,-10));
		
		pool.shutdown();
	}

}
