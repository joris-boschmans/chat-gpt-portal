package com.jorisboschmans.chatgptportal.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorisboschmans.chatgptportal.enums.Model;
import com.jorisboschmans.chatgptportal.gpt.GptApiRequest;
import com.jorisboschmans.chatgptportal.utils.PropManager;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiService {
	
	private static ApiService instance;
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	private ApiService() {}
	
	public static synchronized ApiService getInstance() {
		if (instance == null)
			instance = new ApiService();
		return instance;
	}
	
	@NotNull
	public String post(Model model, String content, String name) throws IOException {
		
		URL url = new URL(PropManager.get("chat-completion-url"));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Authorization", "Bearer " + PropManager.get("api-key-bearer-token"));
		connection.setDoOutput(true);
		
		try (OutputStream os = connection.getOutputStream()) {
			String postData = objectMapper.writeValueAsString(new GptApiRequest(model, content, name));
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
		return response.toString();
	}
	
}
