package org.artorg.tools.phantomData.server;

import org.artorg.tools.phantomData.server.boot.DesktopSwingLauncher;
import org.artorg.tools.phantomData.server.boot.LaunchConfigurationsServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
	
	public static void main(String[] args) {
		new DesktopSwingLauncher().launch(BootApplication.class, LaunchConfigurationsServer.DESKTOP_SWING_BOOT, args);
	}

}
