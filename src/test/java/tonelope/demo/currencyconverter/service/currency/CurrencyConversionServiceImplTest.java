package tonelope.demo.currencyconverter.service.currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tonelope.demo.currencyconverter.dao.currency.ConversionRatesDao;
import tonelope.demo.currencyconverter.exception.CurrencyConversionException;
import tonelope.demo.currencyconverter.model.CurrencyConversionRequest;
import tonelope.demo.currencyconverter.model.CurrencyConversionResponse;
import tonelope.demo.currencyconverter.model.CurrencyInfo;

public class CurrencyConversionServiceImplTest {

	@Mock
	private ConversionRatesDao conversionRatesDao;
	
	@InjectMocks
	private CurrencyConversionServiceImpl testee; 
	
	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testConvert() {
		final Map<String, Double> exchangeRates = new HashMap<>();
		exchangeRates.put("ABC", 1.0);
		Assert.assertEquals(this.testee.convert(2.0, "ABC", "ABC"), 2.0);
	}
	
	@Test
	public void testConvertWithRequest() {
		final Map<String, Double> exchangeRates = new HashMap<>();
		exchangeRates.put("USD", 1.0);
		exchangeRates.put("ABC", 123.0);
		
		Mockito.when(this.conversionRatesDao.getExchangeRatesFromUSD()).thenReturn(exchangeRates);
		Mockito.when(this.conversionRatesDao.getAllCurrencyInfo()).thenReturn(this.getMockCurrencyInfo());
		
		final CurrencyConversionRequest request = new CurrencyConversionRequest();
		request.setValue(2.0);
		request.setFromCode("USD");
		request.setToCode("ABC");
		
		final CurrencyConversionResponse response = this.testee.convert(request);
		
		Mockito.verify(this.conversionRatesDao, Mockito.times(2)).getExchangeRatesFromUSD();
		Mockito.verify(this.conversionRatesDao, Mockito.times(2)).getAllCurrencyInfo();
		
		Assert.assertEquals(response.getValue(), 2.0);
		Assert.assertEquals(response.getExchangeRate(), 123.0);
		Assert.assertEquals(response.getFromCurrency().getCode(), "USD");
		Assert.assertEquals(response.getFromCurrency().getName(), "United States Dollar");
		Assert.assertEquals(response.getFromCurrency().getSymbol(), "$");
		Assert.assertEquals(response.getToCurrency().getCode(), "ABC");
		Assert.assertEquals(response.getToCurrency().getName(), "Funny Money");
		Assert.assertEquals(response.getToCurrency().getSymbol(), "$");
		Assert.assertEquals(response.getConvertedValue(), 246.0);
	}
	
	private List<CurrencyInfo> getMockCurrencyInfo() {
		final List<CurrencyInfo> currencyInfoList = new ArrayList<>();
		
		currencyInfoList.add(new CurrencyInfo());
		currencyInfoList.get(0).setCode("USD");
		currencyInfoList.get(0).setName("United States Dollar");
		currencyInfoList.get(0).setSymbol("$");
		
		currencyInfoList.add(new CurrencyInfo());
		currencyInfoList.get(1).setCode("ABC");
		currencyInfoList.get(1).setName("Funny Money");
		currencyInfoList.get(1).setSymbol("$");
		
		return currencyInfoList;
	}
	
	@Test(expectedExceptions = CurrencyConversionException.class)
	public void testGetCurrencyFromCodeUnknownCurrency() {
		Mockito.when(this.conversionRatesDao.getAllCurrencyInfo()).thenReturn(this.getMockCurrencyInfo());
		this.testee.getCurrencyFromCode("FAKE");
	}
	
	@Test(expectedExceptions = CurrencyConversionException.class)
	public void testGetExchangeRateUnknownCode() {
		Mockito.when(this.conversionRatesDao.getExchangeRatesFromUSD()).thenReturn(new HashMap<String, Double>());
		this.testee.getExchangeRate("FAKE");
	}
	
	@Test(expectedExceptions = CurrencyConversionException.class)
	public void testGetExchangeRateNoCode1() {
		this.testee.getExchangeRate(" ");
	}
	
	@Test(expectedExceptions = CurrencyConversionException.class)
	public void testGetExchangeRateNoCode2() {
		this.testee.getExchangeRate("ABC", " ");
	}
	
	@Test(expectedExceptions = CurrencyConversionException.class)
	public void testGetExchangeRateNoCode3() {
		this.testee.getExchangeRate(" ", "ABC");
	}
}
