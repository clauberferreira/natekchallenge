package com.natek.challenge.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.natek.challenge.domain.Customer;

public class CustomerSerializer extends StdSerializer<Customer> {

	private static final long serialVersionUID = 1L;

	public CustomerSerializer() {
		super(Customer.class);
	}

	@Override
	public void serialize(Customer value, JsonGenerator gen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		gen.writeString(value.getName());
	}

}
