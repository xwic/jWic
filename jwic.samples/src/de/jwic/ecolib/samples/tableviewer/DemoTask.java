package de.jwic.ecolib.samples.tableviewer;

import java.io.Serializable;

/**
 * Represents a row in the demo tasklist. 
 */
public class DemoTask implements Serializable {
	
	private static int nextId = 0;
	public int id = nextId++; // unique id
	public boolean done = false;
	public String title = "";
	public String owner = "";
	public int completed = 0;
	
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
}