package org.artorg.tools.phantomData.server;

import org.artorg.tools.phantomData.server.boot.DesktopSwingBoot;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
	
	public static void main(String[] args) {
		new DesktopSwingBoot().boot(args);
	}

}
