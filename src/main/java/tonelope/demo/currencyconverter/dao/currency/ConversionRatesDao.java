package tonelope.demo.currencyconverter.dao.currency;

import java.util.List;
import java.util.Map;

import tonelope.demo.currencyconverter.model.CurrencyInfo;

public interface ConversionRatesDao {

	/**
	 * Retrieves a mapping of exchange rates, mapped by currency code to their exchange rate.
	 * Calling this method assumes that all rates will be taken from the point of view of exchanging
	 * from the USD (United States Dollar).
	 * 
	 * @return the mapping of currency codes and their exchange rates.
	 */
	Map<String, Double> getExchangeRatesFromUSD();
	
	/**
	 * Retrieves all currency system supported currency information.
	 * @return the list of currency information
	 */
	List<CurrencyInfo> getAllCurrencyInfo();
	
}
