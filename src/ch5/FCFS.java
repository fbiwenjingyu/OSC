package ch5;

import java.util.List;

public class FCFS implements Algorithms {
    private List<Task> tasks;
    
	public FCFS(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public void schedule() {
		CPU cpu = new CPU();
		while(!tasks.isEmpty()) {
			Task task = pickNextTask();
			cpu.run(task, task.getBurst());
		}
	}

	@Override
	public Task pickNextTask() {
		return tasks.remove(0);
	}
	
	

}
