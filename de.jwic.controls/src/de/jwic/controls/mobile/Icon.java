/**
 * 
 */
package de.jwic.controls.mobile;

/**
 * 
 * Class for all the icons in jquery.mobile library
 * 
 * @author vedad
 *
 */
public class Icon {

	public static final Icon ACTION = new Icon("action");
	public static final Icon ALERT = new Icon("alert");
	public static final Icon ARROWD = new Icon("arrow-d");
	public static final Icon ARROWDL = new Icon("arrow-d-l");
	public static final Icon ARROWDR = new Icon("arrow-d-r");
	public static final Icon ARROWL = new Icon("arrow-l");
	public static final Icon ARROWR = new Icon("arrow-r");
	public static final Icon ARROWU = new Icon("arrow-u");
	public static final Icon ARROWUL = new Icon("arrow-u-l");
	public static final Icon ARROWUR = new Icon("arrow-u-r");
	public static final Icon AUDIO = new Icon("audio");
	public static final Icon BACK = new Icon("back");
	public static final Icon BARS = new Icon("bars");
	public static final Icon BULLETS = new Icon("bullets");
	public static final Icon CALENDAR = new Icon("calendar");
	public static final Icon CAMERA = new Icon("camera");
	public static final Icon CARATD = new Icon("carat-d");
	public static final Icon CARATL = new Icon("carat-l");
	public static final Icon CARATR = new Icon("carat-r");
	public static final Icon CARATU = new Icon("carat-u");
	public static final Icon CHECK = new Icon("check");
	public static final Icon CLOCK = new Icon("clock");
	public static final Icon CLOUD = new Icon("cloud");
	public static final Icon COMMENT = new Icon("comment");
	public static final Icon DELETE = new Icon("delete");
	public static final Icon EDIT = new Icon("edit");
	public static final Icon EYE = new Icon("eye");
	public static final Icon FORBIDDEN = new Icon("forbidden");
	public static final Icon FORWARD = new Icon("forward");
	public static final Icon GEAR = new Icon("gear");
	public static final Icon HEART = new Icon("heart");
	public static final Icon HOME = new Icon("home");
	public static final Icon INFO = new Icon("info");
	public static final Icon LOCATION = new Icon("location");
	public static final Icon LOCK = new Icon("lock");
	public static final Icon MAIL = new Icon("mail");
	public static final Icon MINUS = new Icon("minus");
	public static final Icon NAVIGATION = new Icon("navigation");
	public static final Icon PHONE = new Icon("phone");
	public static final Icon PLUS = new Icon("plus");
	public static final Icon POWER = new Icon("power");
	public static final Icon RECYCLE = new Icon("recycle");
	public static final Icon REFRESH = new Icon("refresh");
	public static final Icon SEARCH = new Icon("search");
	public static final Icon SHOP = new Icon("shop");
	public static final Icon STAR = new Icon("star");
	public static final Icon TAG = new Icon("tag");
	public static final Icon USER = new Icon("user");
	public static final Icon VIDEO = new Icon("video");

	private final String code;

	/**
	 * @param code
	 */
	public Icon(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

}
