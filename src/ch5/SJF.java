package ch5;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SJF implements Algorithms {
	private List<Task> tasks;
	 
	public SJF(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public void schedule() {
		sortByBurstTime(tasks);
		CPU cpu = new CPU();
		while(!tasks.isEmpty()) {
			Task task = pickNextTask();
			cpu.run(task, task.getBurst());
		}

	}

	private void sortByBurstTime(List<Task> tasks) {
		Collections.sort(tasks, new Comparator<Task>() {
			@Override
			public int compare(Task o1, Task o2) {
				return o1.getBurst() - o2.getBurst();
			}
		});
		
	}

	@Override
	public Task pickNextTask() {
		return  tasks.remove(0);
	}

}
