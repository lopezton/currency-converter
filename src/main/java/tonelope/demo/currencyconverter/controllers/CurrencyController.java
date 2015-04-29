package tonelope.demo.currencyconverter.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tonelope.demo.currencyconverter.model.CurrencyInfo;
import tonelope.demo.currencyconverter.model.CurrencyConversionRequest;
import tonelope.demo.currencyconverter.model.CurrencyConversionResponse;
import tonelope.demo.currencyconverter.service.currency.CurrencyConversionService;

@RestController
public class CurrencyController {

	@Autowired
	private CurrencyConversionService currencyConversionService;
	
	/**
	 * Used to retrieve a list of all system supported currencies.
	 * @return the list of CurrencyInfo objects, containing all currency information.
	 */
	@RequestMapping(value = "/api/currency/list", method = RequestMethod.GET)
	public List<CurrencyInfo> getCurrencyList() {
		return this.currencyConversionService.getConvertableCurrencies();
	}
	
	/**
	 * Performs the exchange rate conversion using the parameters contained within the provided
	 * <b>currencyConversionRequest</b> object. 
	 * 
	 * @param currencyConversionRequest The currency conversion request object.
	 * @return The currency conversion response object, containing all useful information 
	 * regarding the conversion response.
	 */
	@RequestMapping(value = "/api/currency/convert", method = RequestMethod.POST)
	public CurrencyConversionResponse convertCurrency(@Valid @RequestBody CurrencyConversionRequest currencyConversionRequest) {
		return this.currencyConversionService.convert(currencyConversionRequest);
	}
}
