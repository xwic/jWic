package de.jwic.controls;

import junit.framework.Assert;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.ecolib.actions.Action;
import de.jwic.test.ControlTestCase;

public class ButtonActionTest extends ControlTestCase {

	private static final boolean ACTION_ENABLED = false;
	private static final boolean ACTION_VISIBLE = false;
	private static final String ACTION_TITLE = "TITLE";
	private static final String ACTION_TOOLTIP = "TOOLTIP";
	private static final ImageRef ACTION_ICON_DISABLED = new ImageRef();
	private static final ImageRef ACTION_ICON_ENABLED = new ImageRef();
	private static final String ACTION_EXCEPTION_MESSAGE = "BUTTON_WORKED";

	private Button button;

	@Override
	public Control createControl(IControlContainer container) {
		Action a = new Action() {
			private static final long serialVersionUID = 1L;

			@Override
			public void run() {
				throw new RuntimeException(ACTION_EXCEPTION_MESSAGE);
			}
		};

		a.setEnabled(ACTION_ENABLED);
		a.setVisible(ACTION_VISIBLE);

		a.setTitle(ACTION_TITLE);
		a.setTooltip(ACTION_TOOLTIP);

		a.setIconDisabled(ACTION_ICON_DISABLED);
		a.setIconEnabled(ACTION_ICON_ENABLED);

		button = new Button(container, a);

		return button;
	}

	public void testButtonProps() throws Exception {
		Assert.assertEquals(ACTION_ENABLED, button.isEnabled());
		Assert.assertEquals(ACTION_VISIBLE, button.isVisible());
		Assert.assertEquals(ACTION_TOOLTIP, button.getTooltip());
		Assert.assertEquals(ACTION_TITLE, button.getTitle());

		assertEquals(ACTION_ICON_DISABLED, button.getIconDisabled());
		assertEquals(ACTION_ICON_ENABLED, button.getIconEnabled());

	}

	public void testButtonAction() throws Exception {
		try {
			sendAction(button, "click", "");
			Assert.fail("Button Action does not work...");
		} catch (RuntimeException ex) {
			//to make sure that its the same exception i check the message
			Assert.assertEquals(ACTION_EXCEPTION_MESSAGE, ex.getMessage());
			// I'm Expecting a RuntimeException cause i throw one in the action
			// anonymous inner class
		}
	}
}
