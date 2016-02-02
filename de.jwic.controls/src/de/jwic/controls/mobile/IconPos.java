package de.jwic.controls.mobile;

/**
 * 
 * Enum for possible icon positions
 * 
 * @author vedad
 *
 */
public enum IconPos {

	LEFT("left"), RIGHT("right"), TOP("top"), BOTTOM("bottom"), NONE("none"), NOTEXT("notext");

	private String code;

	private IconPos(String c) {
		code = c;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return getCode();
	}

}
