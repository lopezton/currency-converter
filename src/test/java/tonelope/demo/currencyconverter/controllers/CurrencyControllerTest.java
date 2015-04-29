package tonelope.demo.currencyconverter.controllers;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tonelope.demo.currencyconverter.model.CurrencyConversionRequest;
import tonelope.demo.currencyconverter.model.CurrencyConversionResponse;
import tonelope.demo.currencyconverter.model.CurrencyInfo;
import tonelope.demo.currencyconverter.service.currency.CurrencyConversionService;

public class CurrencyControllerTest {

	@Mock
	private CurrencyConversionService currencyConversionService;
	
	@InjectMocks
	private CurrencyController testee;
	
	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetCurrencyList() {
		final List<CurrencyInfo> currencyInfoList = new ArrayList<>();
		Mockito.when(this.currencyConversionService.getConvertableCurrencies()).thenReturn(currencyInfoList);
		Assert.assertEquals(this.testee.getCurrencyList(), currencyInfoList);
	}
	
	@Test
	public void testConvertCurrency() {
		final CurrencyConversionRequest request = new CurrencyConversionRequest();
		final CurrencyConversionResponse response = new CurrencyConversionResponse();
		Mockito.when(this.currencyConversionService.convert(request)).thenReturn(response);
		Assert.assertEquals(this.testee.convertCurrency(request), response);
	}
}
