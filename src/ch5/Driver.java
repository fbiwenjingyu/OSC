package ch5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Driver {
	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("Usage: java Driver <algorithms> <schedule>");
		}
		
		List<Task> tasks = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[1]));
			String line = null;
			while((line = br.readLine()) != null ) {
				String[] strings = line.split(",\\s*");
				String name = strings[0];
				int priority = Integer.parseInt(strings[1]);
				int burst = Integer.parseInt(strings[2]);
				Task task = new Task(name, priority, burst);
				tasks.add(task);
			}
			br.close();
			
			Algorithms scheduler = null;
			String algoriths = args[0].toUpperCase();
			switch(algoriths) {
				case "FCFS" : 
					scheduler = new FCFS(tasks);
					break;
				case "SJF":
					scheduler = new SJF(tasks);
					break;
				case "PRI":
					scheduler = new Priority(tasks);
					break;
				case "RR":
					scheduler = new RR(tasks);
					break;
				case "PRI-RR":
					scheduler = new PriorityRR(tasks);
					break;
				default:
					System.err.println("Invalid algorithms");
					System.exit(0);
			}
			
			scheduler.schedule();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
