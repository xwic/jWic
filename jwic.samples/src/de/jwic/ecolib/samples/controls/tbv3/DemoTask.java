package de.jwic.ecolib.samples.controls.tbv3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a row in the demo tasklist. 
 */
public class DemoTask implements Serializable {
	
	private static int nextId = 0;
	int id = nextId++; // unique id
	boolean done = false;
	String title = "";
	String owner = "";
	int completed = 0;
	
	List<DemoTask> subTasks = new ArrayList<DemoTask>();
	
	/**
	 * default constructor.
	 */
	public DemoTask() {
		
	}
	/**
	 * @param title
	 * @param owner
	 * @param completed
	 */
	public DemoTask(String title, String owner, int completed) {
		super();
		this.title = title;
		this.owner = owner;
		this.completed = completed;
	}
	
	/** 
	 * Add a SubTask.
	 * @param task
	 */
	public void addSubTask(DemoTask task) {
		subTasks.add(task);
	}
	
	/**
	 * Returns true if this task has subTasks.
	 * @return
	 */
	public boolean hasSubTasks() {
		return !subTasks.isEmpty();
	}
	
}