package com.natek.challenge.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.natek.challenge.domain.Trader;

public class TraderDeserializer extends StdDeserializer<Trader> {
	private static final long serialVersionUID = 1L;

	protected TraderDeserializer() {
		super(Trader.class);
	}

	@Override
	public Trader deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return new Trader(jp.readValueAs(String.class));
	}
}
