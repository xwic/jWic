package de.jwic.controls.mobile;

/**
 * 
 * Enum for possible themes from jquery mobile library
 * 
 * @author vedad
 *
 */
public enum Theme {

	A("a"), B("b"), C("c"), D("d"), E("e"), F("f"), G("g"), H("h"), I("i"), J("j"), K("k"), L("l"), M("m"), N("n"), O("o"), P("p"), Q(
			"q"), R("r"), S("s"), T("t"), U("u"), V("v"), W("w"), X("x"), Y("y"), Z("z");

	private String code;

	private Theme(String c) {
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
