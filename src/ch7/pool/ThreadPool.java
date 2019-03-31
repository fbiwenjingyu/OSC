package ch7.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadPool {
	private int size;
	public static final int DEFAULT_SIZE = 5;
	WorkThread[] workThreads;
	List<Runnable> tasks = new ArrayList<>();
	List<WorkThread> workThreadList = new CopyOnWriteArrayList<>();
	WorkThread singleThread = new WorkThread();
	public ThreadPool() {
		this(DEFAULT_SIZE);
	}
	public ThreadPool(int size) {
		this.size = size;
		workThreads = new WorkThread[this.size];
		for(int i=0;i<this.size;i++) {
			workThreads[i] = new WorkThread();
			workThreadList.add(workThreads[i]);
		}
	}
	
	public void shutdown() {
		
	}
	
	public void add(Runnable task) {
		tasks.add(task);
		WorkThread workThread = null;
		workThread =  getAnIdleWorkThread();
		if(workThread != null) {
			if(hasTasks()) {
				Runnable ts = getATask();
				workThread.setTask(ts);
				workThread.run();
				try {
					Thread.sleep(3000);
					workThread.join();
					workThread.setStatus(Status.STOP);
					Thread.sleep(1000);
					workThread.setStatus(Status.IDLE);
					workThreadList.add(workThread);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void addExtra(Runnable task) {
		tasks.add(task); 
			if(hasTasks()) {
				Runnable ts = getATask();
				singleThread.setTask(ts);
				singleThread.run();
				try {
					Thread.sleep(3000);
					singleThread.join();
					singleThread.setStatus(Status.STOP);
					Thread.sleep(1000);
					singleThread.setStatus(Status.IDLE);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	
	
	private Runnable getATask() {
		return tasks.remove(0);
	}
	private boolean hasTasks() {
		if(tasks.size() > 0) return true;
		return false;
	}
	private WorkThread getAnIdleWorkThread() {
		for(int i=0;i<workThreadList.size();i++) {
			if(workThreadList.get(i).getStatus() == Status.IDLE) {
				return workThreadList.remove(i);
			}
		}
		return null;
	}
	private boolean hasIdleWorkThreads() {
		for(int i=0;i<size;i++) {
			if(workThreads[i].getStatus() == Status.IDLE) {
				return true;
			}
		}
		return false;
	}

	class WorkThread extends Thread{
		private Runnable task;
		private Status status = Status.IDLE;
		@Override
		public void run() {
			task.run();
		}
		public WorkThread(Runnable task) {
			this.task = task;
		}
		public WorkThread() {
			
		}
		public Runnable getTask() {
			return task;
		}
		public void setTask(Runnable task) {
			this.task = task;
		}
		public Status getStatus() {
			return status;
		}
		public void setStatus(Status status) {
			this.status = status;
		}
	}
	
	enum Status{
		IDLE,RUNNING,STOP
	}
}
