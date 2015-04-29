package tonelope.demo.currencyconverter.service.currency;

import java.util.List;

import tonelope.demo.currencyconverter.model.CurrencyInfo;
import tonelope.demo.currencyconverter.model.CurrencyConversionRequest;
import tonelope.demo.currencyconverter.model.CurrencyConversionResponse;

public interface CurrencyConversionService {

	/**
	 * Converts a double value to its exchanged rate, by retrieving the exchange rate from 
	 * <b>fromCode</b> to <b>toCode</b>.
	 * 
	 * @param value the value to convert.
	 * @param fromCode the currency code to convert from.
	 * @param toCode the currency code to convert to.
	 * @return the converted value.
	 */
	double convert(double value, String fromCode, String toCode);

	/**
	 * Fetches the exchange rate from <b>fromCode</b> to <b>toCode</b>.
	 * 
	 * @param fromCode the currency code to exchange from.
	 * @param toCode the currency code to exchange to.
	 * @return the exchange rate.
	 */
	double getExchangeRate(String fromCode, String toCode);

	/**
	 * Fetches the exchange rate from <b>USD (United States Dollar)</b> to <b>toCode</b>.
	 * @param toCode The currency code to exchange to.
	 * @return the exchange rate.
	 */
	double getExchangeRate(String toCode);

	/**
	 * Fetches the system supported currencies that are convertable.
	 * @return the list of convertable currencies.
	 */
	List<CurrencyInfo> getConvertableCurrencies();

	/**
	 * Converts a double value to its exchanged rate, by retrieving the exchange rate from 
	 * <b>fromCode</b> to <b>toCode</b>, contained within the <b>request</b> object.
	 * @param request The CurrencyConversionRequest object to use in conversion.
	 * @return The converted rate.
	 */
	CurrencyConversionResponse convert(CurrencyConversionRequest request);

	/**
	 * Fetches the CurrencyInfo object by currency code. Useful for retriving more information
	 * about a currency, when only obtaining it's unique code.
	 * 
	 * @param currencyCode the currency code to search for
	 * @return the currency information object
	 */
	CurrencyInfo getCurrencyFromCode(String currencyCode);

}
