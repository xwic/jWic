package de.jwic.mobile.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;

/**
 * Created by boogie on 10/30/14.
 */
public final class FieldSetLayout extends ControlContainer{

	private Type type = Type.Vertical;

	public FieldSetLayout(IControlContainer container) {
		this(container, null);
	}

	public FieldSetLayout(IControlContainer container, String name) {
		super(container, name);
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

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
}
