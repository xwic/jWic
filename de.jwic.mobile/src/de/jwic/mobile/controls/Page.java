package de.jwic.mobile.controls;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;
import de.jwic.base.JavaScriptSupport;
import de.jwic.mobile.common.properties.WithTextProperty;
import org.apache.commons.lang.StringUtils;

/**
 * Created by boogie on 10/27/14.
 */
@JavaScriptSupport
public class Page extends ControlContainer implements IOuterLayout, WithTextProperty{

	private String text;

	public Page(IControlContainer container, String name) {
		super(container, name);
		this.setRendererId(Control.DEFAULT_OUTER_RENDERER);
		this.setTemplateName(ControlContainer.class.getName());
		this.text = StringUtils.isEmpty(name) ? "" : name;
	}

	public Page(IControlContainer container) {
		this(container, null);
	}

	@Override
	public final String getOuterTemplateName() {
		return Page.class.getName();
	}

	@Override
	public void setText(String title) {
		this.text = title;
		this.requireRedraw();
	}

	@Override
	public String getText() {
		return this.text;
	}

}
