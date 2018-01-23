package com.natek.challenge.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.natek.challenge.domain.LegalEntity;

public class LegalEntityDeserializer extends StdDeserializer<LegalEntity> {
	private static final long serialVersionUID = 1L;

	protected LegalEntityDeserializer() {
		super(LegalEntity.class);
	}

	@Override
	public LegalEntity deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return new LegalEntity(jp.readValueAs(String.class));
	}
}
