package com.jorisboschmans.chatgptportal;

import com.jorisboschmans.chatgptportal.services.GptService;
import com.jorisboschmans.chatgptportal.shutdownhooks.ShutdownJavalin;
import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;

import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.path;

public class App {
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> config.plugins.enableCors(cors -> cors.add(CorsPluginConfig::anyHost))).start(7000);
		
		Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownJavalin(app)));
		
		app.routes(() ->
				path("/gpt", () -> {
					post("/3", ctx -> GptService.getInstance().gpt3(ctx));
					post("/4", ctx -> GptService.getInstance().gpt4(ctx));
				})
		);
		
		
	}
	
}
