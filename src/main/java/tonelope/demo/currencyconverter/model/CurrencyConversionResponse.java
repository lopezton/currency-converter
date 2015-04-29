package tonelope.demo.currencyconverter.model;

public class CurrencyConversionResponse {

	private double value;
	private CurrencyInfo fromCurrency;
	private CurrencyInfo toCurrency;
	private double exchangeRate;
	private double convertedValue;
	
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
	 * @return the fromCurrency
	 */
	public CurrencyInfo getFromCurrency() {
		return fromCurrency;
	}
	
	/**
	 * @param fromCurrency the fromCurrency to set
	 */
	public void setFromCurrency(CurrencyInfo fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	
	/**
	 * @return the toCurrency
	 */
	public CurrencyInfo getToCurrency() {
		return toCurrency;
	}
	
	/**
	 * @param toCurrency the toCurrency to set
	 */
	public void setToCurrency(CurrencyInfo toCurrency) {
		this.toCurrency = toCurrency;
	}
	
	/**
	 * @return the exchangeRate
	 */
	public double getExchangeRate() {
		return exchangeRate;
	}
	
	/**
	 * @param exchangeRate the exchangeRate to set
	 */
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	/**
	 * @return the convertedValue
	 */
	public double getConvertedValue() {
		return convertedValue;
	}
	
	/**
	 * @param convertedValue the convertedValue to set
	 */
	public void setConvertedValue(double convertedValue) {
		this.convertedValue = convertedValue;
	}
}
