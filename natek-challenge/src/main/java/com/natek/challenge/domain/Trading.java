package com.natek.challenge.domain;

import java.time.LocalDate;
import java.util.Currency;
import java.util.EnumSet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.natek.challenge.util.CurrencyDeserializer;
import com.natek.challenge.util.CurrencySerializer;
import com.natek.challenge.util.CustomerDeserializer;
import com.natek.challenge.util.CustomerSerializer;
import com.natek.challenge.util.LegalEntityDeserializer;
import com.natek.challenge.util.LegalEntitySerializer;
import com.natek.challenge.util.LocalDateDeserializer;
import com.natek.challenge.util.LocalDateSerializer;
import com.natek.challenge.util.TraderDeserializer;
import com.natek.challenge.util.TraderSerializer;

public class Trading {

	@JsonInclude(Include.NON_DEFAULT)
	private long id;

	@JsonDeserialize(using = CustomerDeserializer.class)
	@JsonSerialize(using = CustomerSerializer.class)
	private Customer customer;

	@JsonIgnore
	private Currency curFrom;

	@JsonIgnore
	private Currency curTo;

	@JsonInclude(Include.NON_NULL)
	private Direction direction;

	@JsonInclude(Include.NON_NULL)
	private Type type;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonInclude(Include.NON_NULL)
	private LocalDate tradeDate;

	@JsonInclude(Include.NON_EMPTY)
	@JsonProperty("amount1")
	private double amountFrom;

	@JsonInclude(Include.NON_EMPTY)
	@JsonProperty("amount2")
	private double amountTo;

	@JsonInclude(Include.NON_EMPTY)
	private double rate;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonInclude(Include.NON_NULL)
	private LocalDate valueDate;

	@JsonInclude(Include.NON_NULL)
	private Style style;

	@JsonInclude(Include.NON_EMPTY)
	private String strategy;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonInclude(Include.NON_NULL)
	private LocalDate deliveryDate;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonInclude(Include.NON_NULL)
	private LocalDate expiryDate;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonInclude(Include.NON_NULL)
	private LocalDate excerciseStartDate;

	@JsonDeserialize(using = CurrencyDeserializer.class)
	@JsonSerialize(using = CurrencySerializer.class)
	@JsonInclude(Include.NON_NULL)
	private Currency payCcy;

	@JsonInclude(Include.NON_EMPTY)
	private double premium;

	@JsonDeserialize(using = CurrencyDeserializer.class)
	@JsonSerialize(using = CurrencySerializer.class)
	@JsonInclude(Include.NON_NULL)
	private Currency premiumCcy;

	@JsonInclude(Include.NON_EMPTY)
	private String premiumType;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonInclude(Include.NON_NULL)
	private LocalDate premiumDate;

	@JsonDeserialize(using = TraderDeserializer.class)
	@JsonSerialize(using = TraderSerializer.class)
	@JsonInclude(Include.NON_NULL)
	private Trader trader;

	@JsonDeserialize(using = LegalEntityDeserializer.class)
	@JsonSerialize(using = LegalEntitySerializer.class)
	@JsonInclude(Include.NON_NULL)
	private LegalEntity legalEntity;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the curFrom
	 */
	public Currency getCurFrom() {
		return curFrom;
	}

	/**
	 * @param curFrom
	 *            the curFrom to set
	 */
	public void setCurFrom(Currency curFrom) {
		this.curFrom = curFrom;
	}

	/**
	 * @return the curTo
	 */
	public Currency getCurTo() {
		return curTo;
	}

	/**
	 * @param curTo
	 *            the curTo to set
	 */
	public void setCurTo(Currency curTo) {
		this.curTo = curTo;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the tradeDate
	 */
	public LocalDate getTradeDate() {
		return tradeDate;
	}

	/**
	 * @param tradeDate
	 *            the tradeDate to set
	 */
	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	/**
	 * @return the amountFrom
	 */
	public double getAmountFrom() {
		return amountFrom;
	}

	/**
	 * @param amountFrom
	 *            the amountFrom to set
	 */
	public void setAmountFrom(double amountFrom) {
		this.amountFrom = amountFrom;
	}

	/**
	 * @return the amountTo
	 */
	public double getAmountTo() {
		return amountTo;
	}

	/**
	 * @param amountTo
	 *            the amountTo to set
	 */
	public void setAmountTo(double amountTo) {
		this.amountTo = amountTo;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the valueDate
	 */
	public LocalDate getValueDate() {
		return valueDate;
	}

	/**
	 * @param valueDate
	 *            the valueDate to set
	 */
	public void setValueDate(LocalDate valueDate) {
		this.valueDate = valueDate;
	}

	/**
	 * @return the style
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * @param style
	 *            the style to set
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	/**
	 * @return the strategy
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * @param strategy
	 *            the strategy to set
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	/**
	 * @return the deliveryDate
	 */
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate
	 *            the deliveryDate to set
	 */
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the expiryDate
	 */
	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the excerciseStartDate
	 */
	public LocalDate getExcerciseStartDate() {
		return excerciseStartDate;
	}

	/**
	 * @param excerciseStartDate
	 *            the excerciseStartDate to set
	 */
	public void setExcerciseStartDate(LocalDate excerciseStartDate) {
		this.excerciseStartDate = excerciseStartDate;
	}

	/**
	 * @return the payCcy
	 */
	public Currency getPayCcy() {
		return payCcy;
	}

	/**
	 * @param payCcy
	 *            the payCcy to set
	 */
	public void setPayCcy(Currency payCcy) {
		this.payCcy = payCcy;
	}

	/**
	 * @return the premium
	 */
	public double getPremium() {
		return premium;
	}

	/**
	 * @param premium
	 *            the premium to set 
	 */
	public void setPremium(double premium) {
		this.premium = premium;
	}

	/**
	 * @return the premiumCcy
	 */
	public Currency getPremiumCcy() {
		return premiumCcy;
	}

	/**
	 * @param premiumCcy
	 *            the premiumCcy to set
	 */
	public void setPremiumCcy(Currency premiumCcy) {
		this.premiumCcy = premiumCcy;
	}

	/**
	 * @return the premiumType
	 */
	public String getPremiumType() {
		return premiumType;
	}

	/**
	 * @param premiumType
	 *            the premiumType to set
	 */
	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}

	/**
	 * @return the premiumDate
	 */
	public LocalDate getPremiumDate() {
		return premiumDate;
	}

	/**
	 * @param premiumDate
	 *            the premiumDate to set
	 */
	public void setPremiumDate(LocalDate premiumDate) {
		this.premiumDate = premiumDate;
	}

	/**
	 * @return the trader
	 */
	public Trader getTrader() {
		return trader;
	}

	/**
	 * @param trader
	 *            the trader to set
	 */
	public void setTrader(Trader trader) {
		this.trader = trader;
	}

	/**
	 * @return the legalEntity
	 */
	public LegalEntity getLegalEntity() {
		return legalEntity;
	}

	/**
	 * @param legalEntity
	 *            the legalEntity to set
	 */
	public void setLegalEntity(LegalEntity legalEntity) {
		this.legalEntity = legalEntity;
	}

	@Override
	public int hashCode() {
		return (int) id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Trading) {
			return ((Trading) obj).getId() == id;
		}
		return false;
	}

	@JsonGetter
	public String getccyPair() {
		return curFrom.getCurrencyCode().concat(curTo.getCurrencyCode());
	}

	@JsonSetter
	public void setccyPair(String ccyPair) {
		curFrom = Currency.getInstance(ccyPair.substring(0, 3));
		curTo = Currency.getInstance(ccyPair.substring(3, 6));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Trading [id=".concat(String.valueOf(id)).concat(", customer=").concat(customer.getName()).concat("]");
	}

	public enum Direction {
		BUY, SELL;

		@JsonCreator
		public static Type fromString(String key) {
			return EnumSet.allOf(Type.class).stream().filter(e -> e.name().equalsIgnoreCase(key)).findFirst()
					.orElse(null);
		}
	}

	public enum Type {
		SPOT, FORWARD, OPTIONS;
		@JsonCreator
		public static Type fromString(String key) {
			return EnumSet.allOf(Type.class).stream().filter(e -> e.name().equalsIgnoreCase(key)).findFirst()
					.orElse(null);
		}
	}

	public enum Style {
		AMERICAN, EUROPEAN;
		@JsonCreator
		public static Type fromString(String key) {
			return EnumSet.allOf(Type.class).stream().filter(e -> e.name().equalsIgnoreCase(key)).findFirst()
					.orElse(null);
		}
	}

	/*
	 * private Currency convertCurrency(String value) { if (value != null &&
	 * !value.isEmpty()) { try { return Currency.getInstance(value.toUpperCase()); }
	 * catch (IllegalArgumentException e) { return null; } } return null; }
	 */

}