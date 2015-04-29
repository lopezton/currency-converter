package tonelope.demo.currencyconverter.dao.currency;

import java.util.List;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tonelope.demo.currencyconverter.model.CurrencyInfo;

public class OERConversionRatesDaoImplTest {

	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private OERConversionRatesDaoImpl testee;
	
	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.testee.setCurrencyConverterUrl("http://localhost:8080/api/");
		this.testee.setOerAccessKey("myKey");
	}
	
	@Test
	public void testGetExchangeRatesFromUSD() {
		Mockito.when(this.restTemplate.getForObject("http://localhost:8080/api/latest.json?app_id=myKey", String.class)).thenReturn(" { \"rates\": { \"USD\" : 1.0, \"ABC\" : 2.0 } }");
		final Map<String, Double> result = this.testee.getExchangeRatesFromUSD();
		Mockito.verify(this.restTemplate).getForObject("http://localhost:8080/api/latest.json?app_id=myKey", String.class);
		Assert.assertEquals(result.size(), 2);
		Assert.assertEquals(result.get("USD"), 1.0);
		Assert.assertEquals(result.get("ABC"), 2.0);
	}
	
	@Test
	public void testGetExchangeRatesFromUSDWithError() {
		Mockito.when(this.restTemplate.getForObject("http://localhost:8080/api/latest.json?app_id=myKey", String.class)).thenReturn("{");
		final Map<String, Double> result = this.testee.getExchangeRatesFromUSD();
		Mockito.verify(this.restTemplate).getForObject("http://localhost:8080/api/latest.json?app_id=myKey", String.class);
		Assert.assertNull(result);
	}
	
	@Test
	public void testGetAllCurrencyInfo() {
		Mockito.when(this.restTemplate.getForObject("http://localhost:8080/api/currencies.json", String.class)).thenReturn("{ \"USD\" : \"United States Dollar\", \"ABC\" : \"Funny Money\" }");
		final List<CurrencyInfo> result = this.testee.getAllCurrencyInfo();
		Mockito.verify(this.restTemplate).getForObject("http://localhost:8080/api/currencies.json", String.class);
		Assert.assertEquals(result.size(), 2);
		Assert.assertEquals(result.get(1).getCode(), "USD");
		Assert.assertEquals(result.get(1).getName(), "United States Dollar");
		Assert.assertEquals(result.get(0).getCode(), "ABC");
		Assert.assertEquals(result.get(0).getName(), "Funny Money");
	}
	
	@Test
	public void testGetAllCurrencyInfoWithError() {
		Mockito.when(this.restTemplate.getForObject("http://localhost:8080/api/currencies.json", String.class)).thenReturn("{");
		final List<CurrencyInfo> result = this.testee.getAllCurrencyInfo();
		Mockito.verify(this.restTemplate).getForObject("http://localhost:8080/api/currencies.json", String.class);
		Assert.assertNull(result);
	}
}
