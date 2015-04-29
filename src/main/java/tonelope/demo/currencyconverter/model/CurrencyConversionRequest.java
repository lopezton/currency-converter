package tonelope.demo.currencyconverter.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class CurrencyConversionRequest {

	@Min(value = 0)
	private double value;
	
	@NotEmpty
	private String fromCode;
	
	@NotEmpty
	private String toCode;
	
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * @return the fromCode
	 */
	public String getFromCode() {
		return fromCode;
	}
	
	/**
	 * @param fromCode the fromCode to set
	 */
	public void setFromCode(String fromCode) {
		this.fromCode = fromCode;
	}
	
	/**
	 * @return the toCode
	 */
	public String getToCode() {
		return toCode;
	}
	
	/**
	 * @param toCode the toCode to set
	 */
	public void setToCode(String toCode) {
		this.toCode = toCode;
	}
}
