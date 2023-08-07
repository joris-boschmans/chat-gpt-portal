package com.jorisboschmans.models.gpt;

import com.jorisboschmans.enums.Model;

import java.util.Arrays;
import java.util.Objects;

public class GptApiRequest {
	
	private final Model model;
	
	private final GptApiRequestMessage[] messages;
	
	public GptApiRequest(Model model, String content, String name) {
		this.model = model;
		this.messages = new GptApiRequestMessage[] {new GptApiRequestMessage("user", content, name)};
	}
	
	public String getModel() {
		return model.getValue();
	}
	
	public GptApiRequestMessage[] getMessages() {
		return messages;
	}
	
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GptApiRequest that = (GptApiRequest) o;
		return model == that.model && Arrays.equals(messages, that.messages);
	}
	
	@Override public int hashCode() {
		int result = Objects.hash(model);
		result = 31 * result + Arrays.hashCode(messages);
		return result;
	}
	
}
