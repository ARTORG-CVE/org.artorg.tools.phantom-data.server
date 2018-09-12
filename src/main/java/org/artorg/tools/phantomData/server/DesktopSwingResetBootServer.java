package org.artorg.tools.phantomData.server;

import org.artorg.tools.phantomData.server.boot.SwingConsoleStartupServerBooter;
import org.artorg.tools.phantomData.server.model.PhantomFile;

public class DesktopSwingResetBootServer extends SwingConsoleStartupServerBooter {
	
	{
		setBootApplicationClass(BootApplication.class);
		setExternalConfigOverridable(true);
	}
	
	public static void main(String[] args) {
		new DesktopSwingResetBootServer().boot(args);
	}

	public void boot(String[] args) {
		catchedBoot(() -> {
			init();
			prepareFileStructure();
			PhantomFile.setFilesPath(getFilesPath());
			
//			consoleFrame.setVisible(true);
			shutdownSpringServer();
			deleteFileStructure();
			
			init();
			prepareFileStructure();
			// logInfos();
			startupFrame.setnConsoleLines(191);
			startupFrame.startProgress();
			if (!isConnected())
				startupFrame.setVisible(true);
			startSpringServer(args);

			startupFrame.setVisible(false);
			startupFrame.dispose();
		});
	}
	
}
