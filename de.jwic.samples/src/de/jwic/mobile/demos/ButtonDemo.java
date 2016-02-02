package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.controls.mobile.Icon;
import de.jwic.controls.mobile.IconPos;
import de.jwic.controls.mobile.MButton;
import de.jwic.controls.mobile.Theme;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.mobile.MobileDemoModule;

/**
 * Created by boogie on 10/29/14.
 */
public final class ButtonDemo extends MobileDemoModule {

	public ButtonDemo() {
		super("Button Demo");
	}

	/**
	 * @param controlContainer
	 * @return
	 */
	/* (non-Javadoc)
	 * @see de.jwic.mobile.MobileDemoModule#createPage(de.jwic.base.IControlContainer)
	 */
	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "buttonContainer");
		
		final MButton disabled = new MButton(container, "disabled");
		disabled.setEnabled(false);
		disabled.setTitle("I'm a disable/enable button!");
		
		final MButton disabled2 = new MButton(container, "enabled");
		disabled2.setTitle("I enable/disable button above");
		disabled2.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				
				if(disabled.isEnabled()){
					disabled.setEnabled(false);
					disabled.setTitle("Now I'm disabled...");
				} else{
					disabled.setEnabled(true);
					disabled.setTitle("Now I'm enabled again");
				}
			}
		});

		final MButton mbutton = new MButton(container, "button");
		mbutton.setTitle("Click me, I'm a corners button");
		mbutton.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				if (mbutton.isCorners()){
					mbutton.setCorners(false);
					mbutton.setTitle("Now I don't have corners");
				} else {
					mbutton.setCorners(true);
					mbutton.setTitle("Now I have corners again");
				}
			}
		});
		
		final MButton mbutton2 = new MButton(container, "button2");
		mbutton2.setTitle("Click me, I'm a inline button");
		mbutton2.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				if (mbutton2.isInline())
					mbutton2.setInline(false);
				else
					mbutton2.setInline(true);				
			}
		});
		
		final MButton mbutton3 = new MButton(container, "button3");
		mbutton3.setTitle("Click me, I'm a shadow button");
		mbutton3.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				if (mbutton3.isShadow())
					mbutton3.setShadow(false);
				else
					mbutton3.setShadow(true);				
			}
		});
		
		final MButton mbutton4 = new MButton(container, "button4");
		mbutton4.setTitle("Click me, I'm a mini button");
		mbutton4.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				if (mbutton4.isMini())
					mbutton4.setMini(false);
				else
					mbutton4.setMini(true);			
			}
		});
		
		final MButton mbutton5 = new MButton(container, "button5");
		mbutton5.setTitle("Click me, I'm a icon button");
		mbutton5.setIconClass(Icon.STAR);
		mbutton5.setIconpos(IconPos.RIGHT);

		return container;
	}
}
