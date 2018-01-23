package com.natek.challenge.business;

import com.natek.challenge.domain.Trading;
import com.natek.challenge.exception.TradingException;

/**
 * Business contract to validate a trading object against a variety of business
 * rules in order to assure a consistent data
 * 
 * @author Clauber
 *
 */
public interface TradingBusiness {

	/**
	 * Validate a trading object against all applicable rules
	 * 
	 * @param trading
	 * @throws TradingException
	 */
	void validateTrading(Trading trading) throws TradingException;

}
