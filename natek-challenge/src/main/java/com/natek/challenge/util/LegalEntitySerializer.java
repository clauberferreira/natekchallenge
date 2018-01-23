package com.natek.challenge.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.natek.challenge.domain.LegalEntity;

public class LegalEntitySerializer extends StdSerializer<LegalEntity> {

	private static final long serialVersionUID = 1L;

	public LegalEntitySerializer() {
		super(LegalEntity.class);
	}

	@Override
	public void serialize(LegalEntity value, JsonGenerator gen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		gen.writeString(value.getName());
	}

}
