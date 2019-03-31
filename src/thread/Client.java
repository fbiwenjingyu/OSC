package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Client {
   public static void main(String[] args) {
	Callable<String> callable = new Callable<String>() {
		
		@Override
		public String call() throws Exception {
			
			return "hello world";
		}
	};
	
	
	FutureTask<String> task = new FutureTask<>(callable);
	
	ExecutorService pool = Executors.newFixedThreadPool(5);
	
	pool.submit(task);
	
	try {
		String string = task.get();
		System.out.println(string);
	} catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	pool.shutdown();
}
}
