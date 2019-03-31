package ch5;

import java.util.concurrent.atomic.AtomicInteger;

public class Task {
	private String name;
	private int tid;
	private int priority;
	private int burst;
	
	private static AtomicInteger tidAllocator = new AtomicInteger();
	
	public Task(String name, int priority, int burst) {
		this.name = name;
		this.priority = priority;
		this.burst = burst;
		this.tid = tidAllocator.getAndIncrement();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getBurst() {
		return burst;
	}
	public void setBurst(int burst) {
		this.burst = burst;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + burst;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + priority;
		result = prime * result + tid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (burst != other.burst)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority != other.priority)
			return false;
		if (tid != other.tid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Task [name=" + name + ", tid=" + tid + ", priority=" + priority + ", burst=" + burst + "]";
	}
	
	
	
}
