package de.jwic.controls.chart.api;

/**
 * String - Animation easing effect
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 14.11.2015
 */
public enum AnimationEffect {

	EASEINOUTQUART("easeInOutQuart"), LINEAR("linear"), EASEOUTBOUNCE(
			"easeOutBounce"), EASEINBACK("easeInBack"), EASEINOUTQUAD(
			"easeInOutQuad"), EASEOUTQUART("easeOutQuart"), EASEOUTQUAD(
			"easeOutQuad"), EASEINOUTBOUNCE("easeInOutBounce"), EASEOUTSINE(
			"easeOutSine"), EASEINOUTCUBIC("easeInOutCubic"), EASEINEXPO(
			"easeInExpo"), EASEINOUTBACK("easeInOutBack"), EASEINCIRC(
			"easeInCirc"), EASEINOUTELASTIC("easeInOutElastic"), EASEOUTBACK(
			"easeOutBack"), EASEINQUAD("easeInQuad"), EASEINOUTEXPO(
			"easeInOutExpo"), EASEINQUART("easeInQuart"), EASEOUTQUINT(
			"easeOutQuint"), EASEINOUTCIRC("easeInOutCirc"), EASEINSINE(
			"easeInSine"), easeOutExpo("easeOutExpo"), easeOutCirc(
			"easeOutCirc"), easeOutCubic("easeOutCubic"), easeInQuint(
			"easeInQuint"), EASEINELASTIC("easeInElastic"), EASEINOUTSINE(
			"easeInOutSine"), EASEINOUTQUINT("easeInOutQuint"), EASEINBOUNCE(
			"easeInBounce"), EASEOUTELASTIC("easeOutElastic"), EASEINCUBIC(
			"easeInCubic");

	private String name;

	AnimationEffect(String name) {
		this.name = name;
	}

	/**
	 * get the AninamtionEffect enumeration after the name property
	 * @param name
	 * @return
	 */
	public static AnimationEffect getAfterName(String name) {
		return AnimationEffect.valueOf(name.toUpperCase());
	}

	/**
	 * 
	 * @return property name
	 */
	public String getName() {
		return name;
	}
    
	

}
