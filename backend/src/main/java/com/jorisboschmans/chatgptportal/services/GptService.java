package com.jorisboschmans.chatgptportal.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorisboschmans.chatgptportal.enums.Model;
import com.jorisboschmans.chatgptportal.gpt.Gpt3Request;
import com.jorisboschmans.chatgptportal.gpt.Gpt4Request;
import io.javalin.http.Context;

import java.io.IOException;

public class GptService {

	private static GptService instance;
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	private GptService() {}
	
	public static synchronized GptService getInstance() {
		if (instance == null)
			instance = new GptService();
		return instance;
	}
	
	public void gpt3(Context ctx) throws IOException {
		Gpt3Request gpt3Request = objectMapper.readValue(ctx.body(), Gpt3Request.class);
		
		String response = ApiService.getInstance().post(Model.GPT_3_5_TURBO, gpt3Request.content(), gpt3Request.name());
		
		ctx.status(200).result(response);
	}
	
	public void gpt4(Context ctx) throws IOException {
		Gpt4Request gpt4Request = objectMapper.readValue(ctx.body(), Gpt4Request.class);
		
		String response = ApiService.getInstance().post(Model.GPT_4, gpt4Request.content(), gpt4Request.name());
		
		ctx.status(200).result(response);
	}
	
	
	
}
