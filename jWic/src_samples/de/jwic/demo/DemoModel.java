/**
 * 
 */
package de.jwic.demo;

import java.util.List;

import de.jwic.util.SerObservable;

/**
 * @author lippisch
 *
 */
public class DemoModel extends SerObservable {

	private List<DemoModule> modules;
	private DemoModule activeModule = null;
	
	
	
	/**
	 * @param modules
	 */
	public DemoModel(List<DemoModule> modules) {
		super();
		this.modules = modules;
	}



	/**
	 * @return the modules
	 */
	public List<DemoModule> getModules() {
		return modules;
	}



	/**
	 * @return the activeModule
	 */
	public DemoModule getActiveModule() {
		return activeModule;
	}



	/**
	 * @param activeModule the activeModule to set
	 */
	public void setActiveModule(DemoModule activeModule) {
		this.activeModule = activeModule;
		setChanged();
		notifyObservers();
	}
	
	
	
	
}
