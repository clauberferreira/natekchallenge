package com.natek.challenge.domain;

import java.util.Random;

public class LegalEntity {

	private long id;

	private String name;

	public LegalEntity(String name) {
		this.id = new Random().nextLong();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return (int) id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LegalEntity) {
			return ((LegalEntity) obj).getId() == id;
		}
		return false;
	}
}
