package com.natek.challenge.business.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.natek.challenge.business.TradingBusiness;
import com.natek.challenge.domain.Trading;
import com.natek.challenge.exception.TradingException;
import com.natek.challenge.fixerApi.FixerConsumer;
import com.natek.challenge.fixerApi.FixerResult;

/**
 * Business implementation to validate a trading object against a variety of business
 * rules in order to assure a consistent data
 * 
 * @author Clauber
 *
 */
@Component
public class TradingBusinessImpl implements TradingBusiness {

	@Autowired
	private FixerConsumer fixerConsumer;

	/*
	 * (non-Javadoc)
	 * @see com.natek.challenge.business.TradingBusiness#validateTrading(com.natek.challenge.domain.Trading)
	 */
	@Override
	public void validateTrading(Trading trading) throws TradingException {
		TradingException tradingException = new TradingException(trading);

		validateValueDate(trading, tradingException);

		validateCurrencies(trading, tradingException);

		validateWorkingDay(trading, tradingException);

		validateCustomer(trading, tradingException);

		validateProductType(trading, tradingException);

		validateStyle(trading, tradingException);

		validateExerciseStartDate(trading, tradingException);

		validateExpiryAndPremiumDate(trading, tradingException);

		if (tradingException.getExceptions().size() > 0) {
			throw tradingException;
		}
	}

	/**
	 * Validate if value date is lower than trade date
	 * 
	 * @param trading
	 * @param tradingException
	 */
	private void validateValueDate(Trading trading, TradingException tradingException) {
		if (trading.getValueDate() != null && trading.getValueDate().isBefore(trading.getTradeDate())) {
			tradingException.add("Value date cannot be lower than trade date");
		}
	}

	/**
	 * Validate if currencies' codes are valid
	 * 
	 * @param trading
	 * @param tradingException
	 */
	private void validateCurrencies(Trading trading, TradingException tradingException) {
		if (trading.getCurFrom() == null || trading.getCurTo() == null) {
			tradingException.add("The currencies codes are not valid");
		}
	}

	/**
	 * Validate if value date does not fall on the weekend and if quotation date is
	 * a working day
	 * 
	 * @param trading
	 * @param tradingException
	 */
	private void validateWorkingDay(Trading trading, TradingException tradingException) {
		if (trading.getValueDate() != null && EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(trading.getValueDate().getDayOfWeek())) {
			tradingException.add("Value date cannot fall on the weekend");
		} else if (trading.getValueDate() != null && trading.getCurFrom() != null && trading.getCurTo() != null) {
			// I did an assumption whether a returned date is different of a requested date, it's a non-working date for a quotation.
			// I realized that when I pass a weekend or holiday date it returns a previous working date quotation
			FixerResult fixerResult = fixerConsumer.getQuotation(trading.getValueDate(), trading.getCurFrom(),
					trading.getCurTo());
			if (!fixerResult.getDate().isEqual(trading.getValueDate())) {
				tradingException.add("Value date cannot fall on non-working day for currency");
			}
		}
	}

	/**
	 * Validate if a customer is supported
	 * 
	 * @param trading
	 * @param tradingException
	 */
	private void validateCustomer(Trading trading, TradingException tradingException) {
		if (!Arrays.asList("pluto1", "pluto2").contains(trading.getCustomer().getName().toLowerCase())) {
			tradingException.add("The customer '".concat(trading.getCustomer().getName()).concat("' is not supported"));
		}
	}

	/**
	 * Validate if value date is equals to current date for type equals to SPOT and
	 * if value date is forwarded from current date for type equals FORWARD
	 * 
	 * @param trading
	 * @param tradingException
	 */
	private void validateProductType(Trading trading, TradingException tradingException) {
		// I'm not sure if I understood this rule properly. I assumed what I said in the
		// javadoc above this method.
		// SPOT, FORWARD:
		// - validate the value date against the product type

		LocalDate currentDate = LocalDate.of(2016, 10, 9);
		if (Trading.Type.SPOT.equals(trading.getType()) && !currentDate.isEqual(trading.getValueDate())
				|| Trading.Type.FORWARD.equals(trading.getType()) && (!trading.getValueDate().isAfter(currentDate))) {
			tradingException.add("The value date is not allowed for this trading type");
		}
	}

	/**
	 * Validate if a style is valid
	 * 
	 * @param trading
	 * @param tradingException
	 */
	private void validateStyle(Trading trading, TradingException tradingException) {
		if (trading.getStyle() != null && !EnumSet.allOf(Trading.Style.class).contains(trading.getStyle())) {
			tradingException.add("The style must be either 'American' or 'European'");
		}
	}

	/**
	 * Validate if exercise start date is between trade date and expiry date
	 * 
	 * @param trading
	 * @param tradingException
	 */
	private void validateExerciseStartDate(Trading trading, TradingException tradingException) {
		if (Trading.Style.AMERICAN.equals(trading.getStyle())
				&& trading.getTradeDate().isBefore(trading.getExcerciseStartDate())
				&& trading.getExpiryDate().isAfter(trading.getExcerciseStartDate())) {
			tradingException.add("The style must be either 'American' or 'European'");
		}
	}

	/**
	 * Validate if Expiry and Premium date are lower than delivery date
	 * 
	 * @param trading
	 * @param tradingException
	 */
	private void validateExpiryAndPremiumDate(Trading trading, TradingException tradingException) {
		if (Trading.Type.OPTIONS.equals(trading.getType())
				&& trading.getExpiryDate().isBefore(trading.getDeliveryDate())
				&& trading.getPremiumDate().isBefore(trading.getDeliveryDate())) {
			tradingException.add("Expiry date and Premium date must be lower than delivery date");
		}
	}

}
