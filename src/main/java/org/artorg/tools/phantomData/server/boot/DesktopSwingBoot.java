package org.artorg.tools.phantomData.server.boot;

import static org.artorg.tools.phantomData.server.boot.util.BootUtilsServer.startingServer;

import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.boot.util.AbstractBooter;
import org.artorg.tools.phantomData.server.boot.util.BootUtilsServer;

public class DesktopSwingBoot extends AbstractBooter {
	
	{
		CONFIG.setBootApplicationClass(BootApplication.class);
		CONFIG.setExternalConfigOverride(false);
		CONFIG.setConsumer(args -> {
			if (!BootUtilsServer.isConnected(CONFIG))
				LAUNCHER.createAndshowStartupFrame(CONFIG.getBootApplicationClass());
			startingServer(CONFIG, args);
		});
		LAUNCHER.init(CONFIG);
		CONFIG.init();
	}
	
	public boolean boot(String[] args) {
		return LAUNCHER.launch(CONFIG, args);
	}

}
