package tonelope.demo.currencyconverter.exception;

public class CurrencyConversionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public CurrencyConversionException() {
		super();
	}
	
	public CurrencyConversionException(String msg) {
		super(msg);
	}
}
