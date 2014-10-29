package de.jwic.mobile.common.blurable;

import de.jwic.base.Control;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boogie on 10/28/14.
 */
public class BlurHandler<E extends Control & Blurable> implements Blurable{
	private final E control;

	private boolean updateOnBlur;
	private final List<BlurListener> blurListeners;
	private boolean blurred;


	public BlurHandler(E control) {
		this.blurListeners = new ArrayList<BlurListener>();
		this.control = control;
	}

	@Override
	public void blur() {
		doBlur(true);
	}

	public void blurNoRedraw(){
		this.doBlur(false);
	}

	private void doBlur(boolean redraw) {
		if(redraw) {
			this.blurred = true;
			control.requireRedraw();
		}
		for (BlurListener bl : new ArrayList<BlurListener>(this.blurListeners)) {
			bl.onBlur(this);
		}
	}


	@Override
	public void addBlurListener(BlurListener listener) {
		this.blurListeners.add(listener);
	}

	@Override
	public void removeBlurListener(BlurListener listener) {
		this.blurListeners.remove(listener);
	}

	@Override
	public boolean isUpdateOnBlur() {
		return this.updateOnBlur;
	}

	@Override
	public void setUpdateOnBlur(boolean updateOnBlur) {
		this.updateOnBlur = updateOnBlur;
		control.requireRedraw();
	}

	public boolean isBlurred() {
		boolean blurred = this.blurred;
		this.blurred = false;
		return blurred;
	}
}
