package org.artorg.tools.phantomData.server.boot;

import org.artorg.tools.phantomData.server.DesktopSwingBootServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
	
	public static void main(String[] args) {
		new DesktopSwingBootServer().boot(args);
	}

}
