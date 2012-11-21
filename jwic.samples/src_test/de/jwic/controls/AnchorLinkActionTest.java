package de.jwic.controls;

import junit.framework.Assert;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.ecolib.actions.Action;
import de.jwic.test.ControlTestCase;

public class AnchorLinkActionTest extends ControlTestCase {

	private  static final String ACTION_EXCEPTION_MESSAGE = "ANCHOR_LINK_ACTION_WORKED";
	
	private static final boolean ACTION_ENABLED = false;
	private static final boolean ACTION_VISIBLE = false;
	private static final String ACTION_TITLE = "ANCHOR_LINK_TITLE";
	private AnchorLinkControl anchorLinkControl;

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
	

		this.anchorLinkControl = new AnchorLinkControl(container, a);
		return anchorLinkControl;
	}
	
	public void testAnchorLinkProps() throws Exception{
		Assert.assertEquals(ACTION_ENABLED, anchorLinkControl.isEnabled());
		Assert.assertEquals(ACTION_VISIBLE, anchorLinkControl.isVisible());
		Assert.assertEquals(ACTION_TITLE, anchorLinkControl.getTitle());
	}
	
	public void testAnchorLinkClick() throws Exception{
		try{
			this.sendAction(anchorLinkControl, "click","");
		}catch (RuntimeException ex){
			Assert.assertEquals(ACTION_EXCEPTION_MESSAGE, ex.getMessage());
		}
	}
	

}
