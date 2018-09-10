package org.artorg.tools.phantomData.server.boot;

import static org.artorg.tools.phantomData.server.boot.util.BootUtilsServer.deleteDatabase;
import static org.artorg.tools.phantomData.server.boot.util.BootUtilsServer.deleteFileStructure;
import static org.artorg.tools.phantomData.server.boot.util.BootUtilsServer.prepareFileStructure;
import static org.artorg.tools.phantomData.server.boot.util.BootUtilsServer.shutdownServer;
import static org.artorg.tools.phantomData.server.boot.util.BootUtilsServer.startingServer;

import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.boot.util.AbstractBooter;
import org.artorg.tools.phantomData.server.boot.util.BootUtilsServer;

public class DesktopSwingResetBoot extends AbstractBooter {

	{
		CONFIG.setBootApplicationClass(BootApplication.class);
		CONFIG.setExternalConfigOverride(true);
		CONFIG.setConsumer(args -> {
			shutdownServer(CONFIG);
			deleteDatabase(CONFIG);
			deleteFileStructure(CONFIG);
			prepareFileStructure(CONFIG);
			// logInfos();
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
