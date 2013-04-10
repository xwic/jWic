package de.jwic.controls;

import java.io.StringWriter;
import java.text.DecimalFormatSymbols;

import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.IControlContainer;

/**
 * Supports input and formating of individual number formats.
 * @author dotto
 *
 */
public class NumericInputControl extends InputBoxControl {
	private static final long serialVersionUID = 1L;
	private Double number = null;
	private ThousandSeparator thousandSeparator;
	private DecimalSeparator decimalSeparator;
	private DigitalGroup digitalGroup;
	private String symbol;
	private SymbolPlacement symbolPlacement;
	private Double valueMin;
	private Double valueMax;
	private Integer decimalPlaces;
	private Character deciamlSeperatorChar;
	private RoundSetting roundSetting;
	private EmptyDisplay emptyDisplay;
	private LeadingZeroDisplay leadingZeroDisplay;
	private NegativeBracketsDisplay negativeBracketsDisplay;
	private boolean padding = true;
	
	/**
	 * 
	 * @param container
	 * @param name
	 */
	public NumericInputControl(IControlContainer container, String name) {
		super(container, name);
		
		init();
	}
	
	/**
	 * 
	 * @param container
	 */
	public NumericInputControl(IControlContainer container) {
		this(container, null);
	}
	
	/**
	 * Set some defaults
	 */
	private void init(){
		DecimalFormatSymbols symbols = getSessionContext().getDecimalFormat().getDecimalFormatSymbols();
		
		switch(symbols.getDecimalSeparator()){
			case ',':
				setDecimalSeparator(DecimalSeparator.COMMA);
				break;
			case '.':
				setDecimalSeparator(DecimalSeparator.PERIOD);
				break;
			default:
				break;
		}
		
		switch(symbols.getGroupingSeparator()){
			case ',':
				setThousandSeparator(ThousandSeparator.COMMA);
				break;
			case '.':
				setThousandSeparator(ThousandSeparator.PERIOD);
				break;
			case ' ':
				setThousandSeparator(ThousandSeparator.SPACE);
				break;
			case '\'':
				setThousandSeparator(ThousandSeparator.APOSTROPHE);
				break;
			default:
				break;
		}
	}
	
	/**
	 * 
	 * @return 
	 */
	public Double getNumber() {
		if(field.getValue() == null || field.getValue().length() == 0)
			return null;
		return Double.parseDouble(field.getValue());
	}

	/**
	 * 
	 * @param number
	 */
	public void setNumber(Double number) {
		if(number != null){
			field.setValue(String.valueOf(number));
		}else {
			field.setValue("");
		}
		this.number = number;
		this.requireRedraw();
	}

	/**
	 * 
	 * @return
	 */
	public ThousandSeparator getThousandSeparator() {
		return thousandSeparator;
	}

	/**
	 * 
	 * @param thousandSeparator
	 */
	public void setThousandSeparator(ThousandSeparator thousandSeparator) {
		this.thousandSeparator = thousandSeparator;
		requireRedraw();
	}

	/**
	 * 
	 * @return
	 */
	public DecimalSeparator getDecimalSeparator() {
		return decimalSeparator;
	}

	/**
	 * 
	 * @param decimalSeparator
	 */
	public void setDecimalSeparator(DecimalSeparator decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
		requireRedraw();
	}
	
	/**
	 * 
	 * @return
	 */
	public DigitalGroup getDigitalGroup() {
		return digitalGroup;
	}

	/**
	 * 
	 * @param digitalGroup
	 */
	public void setDigitalGroup(DigitalGroup digitalGroup) {
		this.digitalGroup = digitalGroup;
		requireRedraw();
	}

	/**
	 * 
	 * @return
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * desired currency symbol (examples: € or EUR). 
	 * Note: other symbols can be used, such as %, °C, °F, km/h & MPH 
	 * the possibilities are endless.
	 * @param symbol
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
		requireRedraw();
	}

	/**
	 * 
	 * @return
	 */
	public SymbolPlacement getSymbolPlacement() {
		return symbolPlacement;
	}

	/**
	 * 
	 * @param symbolPlacement
	 */
	public void setSymbolPlacement(SymbolPlacement symbolPlacement) {
		this.symbolPlacement = symbolPlacement;
		requireRedraw();
	}

	/**
	 * 
	 * @return
	 */
	public Double getValueMin() {
		return valueMin;
	}

	/**
	 * Enter the minimum value allowed. 
	 * Values can be whole numbers, floating point, positive, zero or negative.
	 * @param valueMin
	 */
	public void setValueMin(Double valueMin) {
		this.valueMin = valueMin;
		requireRedraw();
	}

	/**
	 * 
	 * @return
	 */
	public Double getValueMax() {
		return valueMax;
	}

	/**
	 * Enter the maximum value allowed. 
	 * Values can be whole numbers, floating point, positive, zero or negative.
	 * @param valueMax
	 */
	public void setValueMax(Double valueMax) {
		this.valueMax = valueMax;
		requireRedraw();
	}

	/**
	 * 
	 * @return
	 */
	public Integer getDecimalPlaces() {
		return decimalPlaces;
	}

	/**
	 * Only needed if you want to override the number of decimal places that are set by the vMin & vMax values.
	 * @param decimalPlaces
	 */
	public void setDecimalPlaces(Integer decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
		requireRedraw();
	}
	
	/**
	 *  altDec allows you to declare an alternative key to enter the decimal separator assigned 
	 *  in aDec. Word of caution - use with discretion because it has the potential of being 
	 *  very confusing to your users.
	 *  Please note that the full stop on the numeric pad will enter the decimal separator 
	 *  even when the comma is assigned as the decimal separator (aDec: ',').
	 * @return
	 */
	public Character getDeciamlSeperatorChar() {
		return deciamlSeperatorChar;
	}

	/**
	 * 
	 * @param deciamlSeperatorChar
	 */
	public void setDeciamlSeperatorChar(Character deciamlSeperatorChar) {
		this.deciamlSeperatorChar = deciamlSeperatorChar;
	}

	/**
	 * 
	 * @return
	 */
	public RoundSetting getRoundSetting() {
		return roundSetting;
	}

	/**
	 * 
	 * @param roundSetting
	 */
	public void setRoundSetting(RoundSetting roundSetting) {
		this.roundSetting = roundSetting;
	}

	/**
	 * 
	 * @return
	 */
	public EmptyDisplay getEmptyDisplay() {
		return emptyDisplay;
	}

	/**
	 * 
	 * @param emptyDisplay
	 */
	public void setEmptyDisplay(EmptyDisplay emptyDisplay) {
		this.emptyDisplay = emptyDisplay;
	}

	/**
	 * 
	 * @return
	 */
	public LeadingZeroDisplay getLeadingZeroDisplay() {
		return leadingZeroDisplay;
	}

	/**
	 * 
	 * @param leadingZeroDisplay
	 */
	public void setLeadingZeroDisplay(LeadingZeroDisplay leadingZeroDisplay) {
		this.leadingZeroDisplay = leadingZeroDisplay;
	}

	/**
	 * 
	 * @return
	 */
	public NegativeBracketsDisplay getNegativeBracketsDisplay() {
		return negativeBracketsDisplay;
	}

	/**
	 * 
	 * @param negativeBracketsDisplay
	 */
	public void setNegativeBracketsDisplay(
			NegativeBracketsDisplay negativeBracketsDisplay) {
		this.negativeBracketsDisplay = negativeBracketsDisplay;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isPadding() {
		return padding;
	}

	/**
	 * controls padding of the decimal places.
	 * true	 always pads the decimal with zeros (default)
	 * false	 no padding - rounding occurs when the decimal length exceeds the decimal places
	 * @param padding
	 */
	public void setPadding(boolean padding) {
		this.padding = padding;
	}

	/**
	 * 
	 * @return options which are set as JSON Object.
	 */
	public String getOptions(){
		try{
			StringWriter sw = new StringWriter();
			JSONWriter writer = new JSONWriter(sw);
			writer.object();
			if(getThousandSeparator() != null)
				writer.key("aSep").value(getThousandSeparator().getCode());
			if(getDecimalSeparator() != null)
				writer.key("aDec").value(getDecimalSeparator().getCode());
			if(getDeciamlSeperatorChar() != null)
				writer.key("altDec").value(getDeciamlSeperatorChar());
			if(getDigitalGroup() != null)
				writer.key("dGroup").value(getDigitalGroup().getCode());
			if(getSymbol() != null)
				writer.key("aSign").value(getSymbol());
			if(getSymbolPlacement() != null)
				writer.key("pSign").value(getSymbolPlacement().getCode());
			if(getValueMin() != null)
				writer.key("vMin").value(getValueMin());
			if(getValueMax() != null)
				writer.key("vMax").value(getValueMax());
			if(getDecimalPlaces() != null)
				writer.key("mDec").value(getDecimalPlaces());
			if(getRoundSetting() != null)
				writer.key("mRound").value(getRoundSetting().getCode());
			writer.key("aPad").value(isPadding());
			if(getNegativeBracketsDisplay() != null)
				writer.key("nBracket").value(getNegativeBracketsDisplay().getCode());
			if(getEmptyDisplay() != null)
				writer.key("wEmpty").value(getEmptyDisplay().getCode());
			if(getLeadingZeroDisplay() != null)
				writer.key("lZero").value(getLeadingZeroDisplay().getCode());
			writer.endObject();
			return sw.toString();
		}catch (JSONException e) {
			throw new RuntimeException("Error while configuring NumberInputControl");
		}
	}

	/**
	 * controls the thousand separator (note - the thousand & decimal separators can not be the same)
	 * comma (default)
	 * aSep: '\''	 apostrophe (note: the apostrophe is escaped)
	 * aSep: '.'	 period
	 * aSep: ' '	 space
	 * aSep: ''	 	 none
	 * @author dotto
	 *
	 */
	public enum ThousandSeparator {
		COMMA(","), APOSTROPHE("'"), PERIOD("."), SPACE(" "), NONE("");
		
		private String code;
		 
		 private ThousandSeparator(String c) {
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
	
	/**
	 * controls the digital grouping - the placement of the thousand separator
	 * dGroup: '3'	 produces 333,333,333 (default)
	 * dGroup: '2'	 produces 22,22,22,333 (India's lakhs format on values below 1 billion)
	 * dGroup: '4'	 produces 4,4444,4444 used in some Asian country's
	 * @author dotto
	 *
	 */
	public enum DigitalGroup {
		 TWO(2), THREE(3), FOUR(4);
		 
		 private int code;
		 
		 private DigitalGroup(int c) {
		   code = c;
		 }
		 
		 public int getCode() {
		   return code;
		 }
		 
		 @Override
		public String toString() {
			return Integer.toString(getCode());
		}
	}
	
	/** 
	 * controls the decimal (note - the thousand & decimal separators can not be the same)
	 * aDec: '.'	 period (default)
	 * aDec: ','	 comma
	 * @author dotto
	 *
	 */
	public enum DecimalSeparator {
		COMMA(","), PERIOD(".");
		
		private String code;
		 
		 private DecimalSeparator(String c) {
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
	
	/**
	 * controls the placement of the currency symbol.
	 * pSign: 'p'	 prefix to the left (default)
	 * pSign: 's'	 suffix to the right
	 * @author dotto
	 *
	 */
	public enum SymbolPlacement {
		LEFT("p"), RIGHT("s");
		
		private String code;
		 
		 private SymbolPlacement(String c) {
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
	
	/**
	 * controls the rounding method. To test the various rounding methods please see below. 
	 * 
	 * mRound: 'S'	Round-Half-Up Symmetric (default)
	 * mRound: 'A'	Round-Half-Up Asymmetric
	 * mRound: 's'	 Round-Half-Down Symmetric (lower case s)
	 * mRound: 'a'	 Round-Half-Down Asymmetric (lower case a)
	 * mRound: 'B'	 Round-Half-Even "Bankers Rounding"
	 * mRound: 'U'	 Round Up "Round-Away-From-Zero"
	 * mRound: 'D'	 Round Down "Round-Toward-Zero" - same as truncate
	 * mRound: 'C'	 Round to Ceiling "Toward Positive Infinity"
	 * mRound: 'F'	 Round to Floor "Toward Negative Infinity"
	 * @author dotto
	 *
	 */
	public enum RoundSetting {
		HALF_UP_SYMETRIC("S"), HALF_UP_ASYMETRIC("A"), HALF_DOWN_SYMETRIC("s"),
		HAL_DOWN_ASYMETRIC("a"), HALF_EVEN("B"), ROUND_UP("U"), ROUND_DOWN("D"),
		ROUND_CEILING("C"), ROUND_FLOOR("F");
		
		private String code;
		 
		 private RoundSetting(String c) {
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
	
	/**
	 * controls input display behavior.
	 * 
	 * wEmpty: 'empty'	 allows input to be empty (no value) (default)
	 * wEmpty: 'zero'	 input field will have at least a zero value
	 * wEmpty: 'sign'	 the currency symbol is always present
	 * @author dotto
	 *
	 */
	public enum EmptyDisplay {
		EMPTY("empty"), ZERO("zero"), SIGN("sign");
		
		private String code;
		 
		 private EmptyDisplay(String c) {
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
	
	/**
	 * Controls if leading zeros are allowed
	 * 
	 * lZero: 'allow'	 allows leading zero to be entered. They are removed on focusout event (default)
	 * lZero: 'deny'	 leading zeros not allowed.
	 * lZero: 'keep'	 leading zeros allowed and will be retained on the focusout event
	 * @author dotto
	 *
	 */
	public enum LeadingZeroDisplay {
		ALLOW("allow"), DENY("deny"), KEEP("keep");
		
		private String code;
		 
		 private LeadingZeroDisplay(String c) {
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
	
	/**
	 * Controls if negative values are display with brackets when the input does NOT have focus.
	 * 
	 * nBracket:null	 no brackets used for negative values (default)
	 * nBracket: '(,)'	Parentheses - visable only on 'focusout'
	 * nBracket: '[,]'	Brackets - visable only on 'focusout'
	 * nBracket: '{,}'	Braces - visable only on 'focusout'
	 * nBracket: '<,>'	 Angle brackets - visable only on 'focusout'
	 * @author dotto
	 *
	 */
	public enum NegativeBracketsDisplay {
		PARANTHESES("(,)"), BRACKETS("[,]"), BRACES("{,}"), ANGLE_BRACKETS("<,>");
		
		private String code;
		 
		 private NegativeBracketsDisplay(String c) {
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
	
}
