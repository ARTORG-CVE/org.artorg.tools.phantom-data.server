package org.artorg.tools.phantomData.server;

import org.artorg.tools.phantomData.server.boot.ServerLauncher;
import org.artorg.tools.phantomData.server.boot.LaunchConfigurationsServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
	
	public static void main(String[] args) {
		new ServerLauncher().launch(BootApplication.class, LaunchConfigurationsServer.START_SERVER, args);
		
	}

}
