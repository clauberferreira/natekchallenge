package com.natek.challenge.util;

import java.io.IOException;
import java.util.Currency;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CurrencyDeserializer extends StdDeserializer<Currency> {
	private static final long serialVersionUID = 1L;

	protected CurrencyDeserializer() {
		super(Currency.class);
	}

	@Override
	public Currency deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return Currency.getInstance(jp.readValueAs(String.class));
	}
}
