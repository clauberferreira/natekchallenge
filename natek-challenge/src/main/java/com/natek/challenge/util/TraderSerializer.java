package com.natek.challenge.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.natek.challenge.domain.Trader;

public class TraderSerializer extends StdSerializer<Trader> {

	private static final long serialVersionUID = 1L;

	public TraderSerializer() {
		super(Trader.class);
	}

	@Override
	public void serialize(Trader value, JsonGenerator gen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		gen.writeString(value.getName());
	}

}
