package de.jwic.mobile.controls;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;
import de.jwic.base.JavaScriptSupport;
import de.jwic.common.properties.WithTextProperty;
import org.apache.commons.lang.StringUtils;

/**
 * Created by boogie on 10/27/14.
 */
@JavaScriptSupport(jsTemplate = "de.jwic.mobile.controls.MPage")
public class MPage extends ControlContainer implements IOuterLayout, WithTextProperty{
	private final ControlContainer header;
	private String text;

	public MPage(IControlContainer container, String name) {
		super(container, name);
		this.header = new ControlContainer(this, "header");

		this.setRendererId(Control.DEFAULT_OUTER_RENDERER);
		this.setTemplateName(ControlContainer.class.getName());
		this.text = StringUtils.isEmpty(name) ? "" : name;

	}

	public MPage(IControlContainer container) {
		this(container, null);
	}

	@Override
	public final String getOuterTemplateName() {
		return MPage.class.getName();
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

	public ControlContainer getHeaderContainer() {
		return header;
	}
}
