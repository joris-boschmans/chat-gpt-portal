package com.jorisboschmans;

import com.jorisboschmans.services.GptService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.path;

public class App {
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000);
			
			app.routes(() ->
					path("/gpt", () ->
							post("/3", ctx -> GptService.getInstance().gpt3(ctx))));
			
		
	}
	
}
