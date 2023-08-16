package com.jorisboschmans.chatgptportal.gpt;

public record GptApiRequestMessage(String role,
                                   String content,
                                   String name) {
}
