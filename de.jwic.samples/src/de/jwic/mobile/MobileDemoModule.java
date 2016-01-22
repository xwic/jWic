package de.jwic.mobile;

import java.io.Serializable;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * Created by boogie on 10/29/14.
 */
public abstract class MobileDemoModule implements Serializable {

	private final String title;

	protected MobileDemoModule(String title) {
		this.title = title;
	}

	public abstract Control createPage(IControlContainer controlContainer);

	public String getTitle(){
		return this.title;
	}
}
