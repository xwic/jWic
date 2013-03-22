package de.jwic.controls;

import java.io.StringWriter;

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
	
	/**
	 * 
	 * @param container
	 * @param name
	 */
	public NumericInputControl(IControlContainer container, String name) {
		super(container, name);
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
		if(getDigitalGroup() != null)
			writer.key("dGroup").value(getThousandSeparator().getCode());
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
		COMMA(","), APOSTROPHE("\\'"), PERIOD("."), SPACE(" "), NONE("");
		
		private String code;
		 
		 private ThousandSeparator(String c) {
		   code = c;
		 }
		 
		 public String getCode() {
		   return code;
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
	}
	
}
