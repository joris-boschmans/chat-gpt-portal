package com.jorisboschmans.chatgptportal.shutdownhooks;

import io.javalin.Javalin;

public class ShutdownJavalin implements Runnable {
	
	private final Javalin javalinApp;
	
	public ShutdownJavalin(Javalin javalinApp) {
		this.javalinApp = javalinApp;
	}
	
	@Override
	public void run() {
		if (javalinApp != null)
			javalinApp.close();
	}
}
