package com.natek.challenge.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.natek.challenge.domain.Customer;

public class CustomerDeserializer extends StdDeserializer<Customer> {
	private static final long serialVersionUID = 1L;

	protected CustomerDeserializer() {
		super(Customer.class);
	}

	@Override
	public Customer deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return new Customer(jp.readValueAs(String.class));
	}
}
