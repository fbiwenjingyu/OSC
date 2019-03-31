package ch5;

public class CPU {
	public void run(Task task,int slice) {
		System.out.println("Will run " + task + " in " + slice + " milliseconds");
	}
}
