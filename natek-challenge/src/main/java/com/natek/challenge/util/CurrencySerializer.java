package com.natek.challenge.util;

import java.io.IOException;
import java.util.Currency;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CurrencySerializer extends StdSerializer<Currency> {

	private static final long serialVersionUID = 1L;

	public CurrencySerializer() {
		super(Currency.class);
	}

	@Override
	public void serialize(Currency value, JsonGenerator gen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		gen.writeString(value.getCurrencyCode());
	}

}
