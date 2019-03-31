package ch5;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PriorityRR implements Algorithms {
	private List<Task> tasks;
	private static final int SLICE = 10;
	public PriorityRR(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public void schedule() {
		CPU cpu = new CPU();
		tasks = new CopyOnWriteArrayList<>(tasks);
		sortByPriority(tasks);
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
	
	private void sortByPriority(List<Task> tasks) {
		Collections.sort(tasks, new Comparator<Task>() {
			@Override
			public int compare(Task o1, Task o2) {
				return o1.getPriority() - o2.getPriority();
			}
		});
		
	}
	@Override
	public Task pickNextTask() {
		// TODO Auto-generated method stub
		return null;
	}

}
