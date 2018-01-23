package com.natek.challenge.fixerApi.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.natek.challenge.fixerApi.FixerConsumer;
import com.natek.challenge.fixerApi.FixerResult;

/**
 * Business implementation to consume an external API (Fixer.io) to get a valid
 * currency quotation
 * 
 * @author Clauber
 *
 */
@Component
public class FixerConsumerImpl implements FixerConsumer {

	private final String BASE_URL = "https://api.fixer.io/";

	/**
	 * I used this Map in order to reduce time processing when searching for the same quotation.
	 * It's a real-world cache mechanism. It's just an idea which in a real world would be replaced
	 * by a distributed cache such as Redis or HazelCast 
	 */
	private Map<String, FixerResult> cache = new HashMap<>();

	/*
	 * (non-Javadoc)
	 * @see com.natek.challenge.fixerApi.FixerConsumer#getQuotation(java.time.LocalDate, java.util.Currency, java.util.Currency)
	 */
	@Override
	public FixerResult getQuotation(LocalDate date, Currency curFrom, Currency curTo) {
		String key = DateTimeFormatter.ISO_LOCAL_DATE.format(date).concat(curFrom.getCurrencyCode())
				.concat(curTo.getCurrencyCode());
		if (cache.containsKey(key)) {
			return cache.get(key);
		} else {
			RestTemplate restTemplate = new RestTemplate();
			FixerResult fixerResult = restTemplate.getForObject(
					BASE_URL.concat(DateTimeFormatter.ISO_LOCAL_DATE.format(date)).concat("?symbols=")
							.concat(curFrom.getCurrencyCode()).concat(",").concat(curTo.getCurrencyCode()),
					FixerResult.class);
			cache.put(key, fixerResult);
			return fixerResult;
		}
	}
}
