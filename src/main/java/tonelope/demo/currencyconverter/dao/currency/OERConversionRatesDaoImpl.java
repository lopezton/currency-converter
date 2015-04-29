package tonelope.demo.currencyconverter.dao.currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import tonelope.demo.currencyconverter.model.CurrencyInfo;

@Repository(value = "conversionRatesDao")
public class OERConversionRatesDaoImpl implements ConversionRatesDao {
	
	public static final Logger LOG = LoggerFactory.getLogger(OERConversionRatesDaoImpl.class);

    public static final String CHARSET = "UTF-8";
    public static final String RATES_OBJECT = "rates";

    public static final String URL_RATES_FMT = "%slatest.json?app_id=%s";
    public static final String URL_CURRENCIES_FMT = "%scurrencies.json";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${currency.converter.url}")
    private String currencyConverterUrl;

	@Value("${currency.converter.accesskey}")
    private String oerAccessKey;

    /*
     * (non-Javadoc)
     * @see com.ihg.dec.chinaweb.businessServices.currency.CurrencyConverterService#getExchangeRate(java.lang.String, java.lang.String)
     */
    @Override
    @Cacheable(value = "currency_conversion", key="#root.methodName")
	public Map<String, Double> getExchangeRatesFromUSD() {
    	Map<String, Double> exchangeRates = null;
    	
        try {
        	final String endpoint = String.format(URL_RATES_FMT, this.currencyConverterUrl, this.oerAccessKey);
        	LOG.debug("Attempting to retrieve currency conversion rates from {}.", endpoint);
        	final String response = this.restTemplate.getForObject(endpoint, String.class);
        	LOG.debug("Successfully retrieved currency conversion rates from {}.", endpoint);
            final JSONObject jsonExchangeRates = (JSONObject) new JSONObject(response).get(RATES_OBJECT);
            
            exchangeRates = new HashMap<>();
            @SuppressWarnings("unchecked")
			final Set<String> keySet = (Set<String>) jsonExchangeRates.keySet();
            for(final String key : keySet) {
            	exchangeRates.put(key, jsonExchangeRates.getDouble(key));
            }
        } catch (Exception e) {
            LOG.error("Exception during currency conversion.", e);
        }
        
       return exchangeRates;
    }

    /*
     * (non-Javadoc)
     * @see tonelope.demo.currencyconverter.dao.currency.ConversionRatesDao#getAllCurrencyInfo()
     */
    @Override
    @Cacheable(value = "currency_conversion", key="#root.methodName")
	public List<CurrencyInfo> getAllCurrencyInfo() {
		List<CurrencyInfo> currencyInfoList = null;
		 try {
        	final String endpoint = String.format(URL_CURRENCIES_FMT, this.currencyConverterUrl);
        	LOG.debug("Attempting to retrieve currency information from {}.", endpoint);
        	final String response = this.restTemplate.getForObject(endpoint, String.class);
        	LOG.debug("Successfully retrieved currency information from {}.", endpoint);
            final JSONObject currencyJson = (JSONObject) new JSONObject(response);
            
            currencyInfoList = new ArrayList<>();
            @SuppressWarnings("unchecked")
            final Set<String> keySet = (Set<String>) currencyJson.keySet();
            for(final String key : keySet) {
            	final CurrencyInfo currencyInfo = new CurrencyInfo();
            	currencyInfo.setCode(key);
            	currencyInfo.setName(currencyJson.getString(key));
            	// TODO - Set symbol. Need to find way to dynamically map new currencies to new
            	// symbols.
            	currencyInfoList.add(currencyInfo);
            }
        } catch (Exception e) {
            LOG.error("Exception during currency information retrieval.", e);
        }
		 
		return currencyInfoList;
	}
    

    /**
	 * @return the currencyConverterUrl
	 */
	public String getCurrencyConverterUrl() {
		return currencyConverterUrl;
	}

	/**
	 * @param currencyConverterUrl the currencyConverterUrl to set
	 */
	public void setCurrencyConverterUrl(String currencyConverterUrl) {
		this.currencyConverterUrl = currencyConverterUrl;
	}

	/**
	 * @return the oerAccessKey
	 */
	public String getOerAccessKey() {
		return oerAccessKey;
	}

	/**
	 * @param oerAccessKey the oerAccessKey to set
	 */
	public void setOerAccessKey(String oerAccessKey) {
		this.oerAccessKey = oerAccessKey;
	}
}
