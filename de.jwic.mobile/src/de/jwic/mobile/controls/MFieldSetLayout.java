package de.jwic.mobile.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.common.visible.Visible;

/**
 * Created by boogie on 10/30/14.
 */
public final class MFieldSetLayout extends ControlContainer implements Visible{

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

	@Override
	public void show() {
		this.setVisible(true);
	}

	@Override
	public void hide() {
		this.setVisible(false);
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
