package com.jorisboschmans.enums;

public enum Model {
	
	GPT_3_5_TURBO("gpt-3.5-turbo");
	
	private final String value;
	
	Model(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
