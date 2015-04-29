package tonelope.demo.currencyconverter.service.currency;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import tonelope.demo.currencyconverter.dao.currency.ConversionRatesDao;
import tonelope.demo.currencyconverter.exception.CurrencyConversionException;
import tonelope.demo.currencyconverter.model.CurrencyConversionRequest;
import tonelope.demo.currencyconverter.model.CurrencyConversionResponse;
import tonelope.demo.currencyconverter.model.CurrencyInfo;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

	public static final Logger LOG = LoggerFactory.getLogger(CurrencyConversionServiceImpl.class);
	
	public static final DecimalFormat df = new DecimalFormat("#.##");
	
	@Autowired
	private ConversionRatesDao conversionRatesDao;
	
	/*
	 * (non-Javadoc)
	 * @see tonelope.demo.lunchmate.service.currency.CurrencyConversionService#convert(double, java.lang.String, java.lang.String)
	 */
	@Override
	public double convert(double value, String fromCode, String toCode) {
		return this.convert(value, this.getExchangeRate(fromCode, toCode));
	}
	
	/**
	 * Converts a value into its converted value by multiplying by the exchangeRate.
	 * 
	 * @param value The value to convert
	 * @param exchangeRate The exchange rate
	 * @return The converted value
	 */
	private double convert(double value, double exchangeRate) {
		return Double.valueOf(df.format(value * exchangeRate));
	}
	
	/*
	 * (non-Javadoc)
	 * @see tonelope.demo.currencyconverter.service.currency.CurrencyConversionService#convert(tonelope.demo.currencyconverter.model.CurrencyConversionRequest)
	 */
	@Override
	public CurrencyConversionResponse convert(CurrencyConversionRequest request) {
		final CurrencyConversionResponse response = new CurrencyConversionResponse();
		response.setValue(request.getValue());
		response.setExchangeRate(this.getExchangeRate(request.getFromCode(), request.getToCode()));
		response.setFromCurrency(this.getCurrencyFromCode(request.getFromCode()));
		response.setToCurrency(this.getCurrencyFromCode(request.getToCode()));
		response.setConvertedValue(this.convert(request.getValue(), response.getExchangeRate()));
		return response;
	}
	
	/*
	 * (non-Javadoc)
	 * @see tonelope.demo.currencyconverter.service.currency.CurrencyConversionService#getCurrencyFromCode(java.lang.String)
	 */
	@Override
	@Cacheable(value = "currency_conversion", key = "#root.methodName + #currencyCode")
	public CurrencyInfo getCurrencyFromCode(String currencyCode) {
		final CurrencyInfo currency = new CurrencyInfo();
		currency.setCode(currencyCode);
		
		final List<CurrencyInfo> currencyInfoList = this.getConvertableCurrencies();
		for (final CurrencyInfo currencyInfo : currencyInfoList) {
			if (currencyInfo.getCode().equals(currencyCode)) {
				return currencyInfo;
			}
		}
		
		throw new CurrencyConversionException("Unknown currency code: " + currencyCode);
	}
	
	/*
     * (non-Javadoc)
     * 
     * @see com.ihg.dec.chinaweb.businessServices.currency.CurrencyConverterService#getExchangeRate(java.lang.String, java.lang.String)
     */
    @Override
    @Cacheable(value = "currency_conversion", key = "#root.methodName + #toCode")
	public double getExchangeRate(String toCode) {
        
        if (StringUtils.isEmpty(toCode)) {
        	throw new CurrencyConversionException("toCode must not be empty.");
        }
        
        final Map<String, Double> exchangeRates = this.conversionRatesDao.getExchangeRatesFromUSD();
        for(final Entry<String, Double> entry : exchangeRates.entrySet()) {
        	if (toCode.equals(entry.getKey())) {
        		return entry.getValue();
        	}
        }
        throw new CurrencyConversionException("Unknown currency code: " + toCode);
    }
	
	/*
     * (non-Javadoc)
     * 
     * @see com.ihg.dec.chinaweb.businessServices.currency.CurrencyConverterService#getExchangeRate(java.lang.String, java.lang.String)
     */
    @Override
    @Cacheable(value = "currency_conversion", key = "#root.methodName + #fromCode + #toCode")
	public double getExchangeRate(String fromCode, String toCode) {
        
        if (StringUtils.isEmpty(fromCode)) {
        	throw new CurrencyConversionException("fromCode must not be empty.");
        } else if (StringUtils.isEmpty(toCode)) { 
        	throw new CurrencyConversionException("toCode must not be empty.");
        } else if (fromCode.equalsIgnoreCase(toCode)) {
            return 1;
        }
        
        final double fromRate = this.getExchangeRate(fromCode);
        final double toRate = this.getExchangeRate(toCode);
        return toRate / fromRate;
    }
    
    /*
     * (non-Javadoc)
     * @see tonelope.demo.currencyconverter.service.currency.CurrencyConversionService#getConvertableCurrencies()
     */
	@Override
    @Cacheable(value = "currency_conversion", key="#root.methodName")
    public List<CurrencyInfo> getConvertableCurrencies() {
		return this.conversionRatesDao.getAllCurrencyInfo();
    }
}
