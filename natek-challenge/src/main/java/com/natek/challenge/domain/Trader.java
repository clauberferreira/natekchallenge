package com.natek.challenge.domain;

import java.util.Random;

public class Trader {

	private long id;

	private String name;

	public Trader(String name) {
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
		if (obj instanceof Trader) {
			return ((Trader) obj).getId() == id;
		}
		return false;
	}

}
