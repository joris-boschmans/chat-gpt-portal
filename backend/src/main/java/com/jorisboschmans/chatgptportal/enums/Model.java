package com.jorisboschmans.chatgptportal.enums;

public enum Model {
	
	GPT_3_5_TURBO("gpt-3.5-turbo"),
	GPT_4("gpt-4");
	
	private final String value;
	
	Model(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
