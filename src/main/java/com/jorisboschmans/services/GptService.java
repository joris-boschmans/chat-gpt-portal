package com.jorisboschmans.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorisboschmans.enums.Model;
import com.jorisboschmans.models.gpt.Gpt3Request;
import com.jorisboschmans.models.gpt.GptApiRequest;
import io.javalin.http.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GptService {

	private static GptService instance;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private static final String API_URL = "https://api.openai.com/v1/chat/completions";
	
	private GptService() {}
	
	public static synchronized GptService getInstance() {
		if (instance == null)
			instance = new GptService();
		return instance;
	}
	
	public void gpt3(Context ctx) throws IOException {
		Gpt3Request gpt3Request = objectMapper.readValue(ctx.body(), Gpt3Request.class);
		
		URL url = new URL(API_URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Authorization", "");
		connection.setDoOutput(true);
		
		try (OutputStream os = connection.getOutputStream()) {
			String postData = objectMapper.writeValueAsString(new GptApiRequest(Model.GPT_3_5_TURBO, gpt3Request.content(), gpt3Request.name()));
			System.out.println(postData);
			byte[] input = postData.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}
		
		int responseCode = connection.getResponseCode();
		StringBuilder response = new StringBuilder();
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			System.out.println("Response: " + response);
		} else {
			System.out.println("API request failed with status code: " + responseCode);
		}
		
		connection.disconnect();
		
		ctx.status(200).result(String.valueOf(response));
	}

}
