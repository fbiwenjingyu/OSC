package ch5;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Priority implements Algorithms {
	private List<Task> tasks;
	
	public Priority(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public void schedule() {
		sortByPriority(tasks);
		CPU cpu = new CPU();
		while(!tasks.isEmpty()) {
			Task task = pickNextTask();
			cpu.run(task, task.getBurst());
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
		return tasks.remove(0);
	}

}
