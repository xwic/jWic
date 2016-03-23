/**
 * 
 */
package de.jwic.demo.pojoedit;

import de.jwic.controls.pojoedit.PojoEditable;

/**
 * This is the object that is being edited by the PojoEditor.
 * 
 * @author lippisch
 */
public class MyPojo {

	public enum Status {
		DRAFT,
		IN_PROGRESS,
		COMPLETED
	}
	
	private String title;
	private String description;
	
	private boolean automode;
	private Status status;
	
	private int yourAge;
	
	/**
	 * @return the title
	 */
	@PojoEditable(title = "Title", order = 1)
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	@PojoEditable(title = "Description", multiline = true, order = 2)
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the automode
	 */
	@PojoEditable(title = "Automode", order = 4)
	public boolean isAutomode() {
		return automode;
	}
	/**
	 * @param automode the automode to set
	 */
	public void setAutomode(boolean automode) {
		this.automode = automode;
	}
	/**
	 * @return the status
	 */
	@PojoEditable(title = "Status", order = 3)
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * @return the yourAge
	 */
	@PojoEditable(title = "Your Age", order = 99)
	public int getYourAge() {
		return yourAge;
	}
	/**
	 * @param yourAge the yourAge to set
	 */
	public void setYourAge(int yourAge) {
		this.yourAge = yourAge;
	}
	
}
