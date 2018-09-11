package org.artorg.tools.phantomData.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
	
	public static void main(String[] args) {
		new DesktopSwingBootServer().boot(args);
	}

}
