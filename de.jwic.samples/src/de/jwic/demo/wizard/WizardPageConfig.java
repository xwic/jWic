/**
 * 
 */
package de.jwic.demo.wizard;

import java.io.Serializable;

/**
 * Simple pojo to hold the configuration of each page.
 * @author lippisch
 */
public class WizardPageConfig implements Serializable {

	private String title;
	private String subTitle;
	private String className;
	
	public WizardPageConfig() {
		title = "";
		subTitle = "";
		className = "";
	}
	
	/**
	 * @param title
	 * @param subTitle
	 * @param className
	 */
	public WizardPageConfig(String title, String subTitle, String className) {
		super();
		this.title = title;
		this.subTitle = subTitle;
		this.className = className;
	}

	/**
	 * @return the title
	 */
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
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}
	/**
	 * @param subTitle the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
}
