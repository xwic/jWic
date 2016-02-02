package de.jwic.controls.mobile;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;

/**
 * Created by boogie on 10/30/14.
 */
public final class MFieldSetLayout extends ControlContainer {

	public static enum Type{
		Horizontal("horizontal"),
		Vertical("vertical");

		private final String name;
		Type(String type) {
			this.name= type;
		}

		public String getName() {
			return name;
		}
	}
	
	private Type type = Type.Vertical;

	public MFieldSetLayout(IControlContainer container) {
		this(container, null);
	}

	public MFieldSetLayout(IControlContainer container, String name) {
		super(container, name);
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

}
