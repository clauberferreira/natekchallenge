package com.natek.challenge.fixerApi;

import java.time.LocalDate;
import java.util.Currency;

/**
 * Business contract to consume an external API (Fixer.io) to get a valid
 * currency quotation
 * 
 * @author Clauber
 *
 */
public interface FixerConsumer {

	/**
	 * Get a valid quotation and the rates with the following currencies. In case a
	 * date is a non-working date, it returns a previous valid one
	 * 
	 * @param date
	 * @param curFrom
	 * @param curTo
	 * @return
	 */
	FixerResult getQuotation(LocalDate date, Currency curFrom, Currency curTo);
}
