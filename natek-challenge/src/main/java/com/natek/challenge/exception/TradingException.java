package com.natek.challenge.exception;

import java.util.ArrayList;
import java.util.List;

import com.natek.challenge.domain.Trading;

/**
 * Class to handle all exceptions occurred during a trading validation
 * 
 * @author Clauber
 *
 */
public class TradingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Trading trading;

	private List<TradingException> exceptions;

	/**
	 * Create a new TradingException with the following message
	 * 
	 * @param message
	 */
	public TradingException(Trading trading) {
		exceptions = new ArrayList<>();
		this.trading = trading;
	}

	private TradingException(String message) {
		super(message);
	}

	/**
	 * Add a new TradingException with the following message to the exception's list
	 * 
	 * @param message
	 * @return
	 */
	public void add(String message) {
		exceptions.add(new TradingException(message));
	}

	/**
	 * Return all added exception in the list
	 * 
	 * @return
	 */
	public List<TradingException> getExceptions() {
		return this.exceptions;
	}

	/**
	 * @return the trading
	 */
	public Trading getTrading() {
		return trading;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (trading != null) {
			return "[trading=".concat(trading.toString()).concat("] ").concat(getLocalizedMessage());
		} else {
			return getLocalizedMessage();
		}
	}

}
