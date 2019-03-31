package ch5;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RR implements Algorithms {
	private static final int SLICE = 10;
	private List<Task> tasks;
	
	public RR(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public void schedule() {
		CPU cpu = new CPU();
		tasks = new CopyOnWriteArrayList<>(tasks);
		while(!tasks.isEmpty()) {
			Iterator<Task> iterator = tasks.iterator();
			while(iterator.hasNext()) {
				Task task = iterator.next();
				if(task.getBurst() > SLICE) {
					cpu.run(task, SLICE);
					task.setBurst(task.getBurst() - SLICE);
				}else {
					cpu.run(task, task.getBurst());
					tasks.remove(task);
				}
			}
		}
	}

	@Override
	public Task pickNextTask() {
		return  tasks.get(0);
	}

}
