package com.jorisboschmans.chatgptportal.gpt;

import java.util.Objects;

public class GptApiRequestMessage {
	
	private final String role;
	private final String content;
	private final String name;
	
	public GptApiRequestMessage(String role, String content, String name) {
		this.role = role;
		this.content = content;
		this.name = name;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getName() {
		return name;
	}
	
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GptApiRequestMessage that = (GptApiRequestMessage) o;
		return Objects.equals(role, that.role) && Objects.equals(content, that.content) && Objects.equals(name, that.name);
	}
	
	@Override public int hashCode() {
		return Objects.hash(role, content, name);
	}
	
}
