package org.artorg.tools.phantomData.server;

import org.reflections.Reflections;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
	private static final Reflections reflections = new Reflections("org.artorg.tools.phantomData");

	public static void main(String[] args) {
		new DesktopFxBootServer().boot(args);
	}
	
	public static Reflections getReflections() {
		return reflections;
	}

}
