package org.artorg.tools.phantomData.server;

import org.artorg.tools.phantomData.server.boot.SwingConsoleFrame;
import org.artorg.tools.phantomData.server.boot.SwingConsoleStartupServerBooter;
import org.artorg.tools.phantomData.server.boot.SwingStartupProgressFrame;
import org.artorg.tools.phantomData.server.model.PhantomFile;

import huma.io.ConsoleDiverter;

public class DesktopSwingResetBootServer extends SwingConsoleStartupServerBooter {
	
	public static void main(String[] args) {
		new DesktopSwingResetBootServer().boot(args);
	}

	public void boot(String[] args) {
		setBootApplicationClass(BootApplication.class);
		setExternalConfigOverridable(true);
		setConsoleDiverter(new ConsoleDiverter());
		setConsoleFrame(new SwingConsoleFrame());
		catchedBoot(() -> {
			setStartupFrame(new SwingStartupProgressFrame());
			init();
			prepareFileStructure();
			PhantomFile.setFilesPath(getFilesPath());
			
//			consoleFrame.setVisible(true);
			shutdownSpringServer();
			deleteFileStructure();
			
			init();
			prepareFileStructure();
			// logInfos();
			
			if (!isConnected()) {
				getStartupFrame().setnConsoleLines(191);
				getStartupFrame().setTitle("Phantom Database");
				getStartupFrame().setProgressing(true);
				getStartupFrame().setVisible(true);
			}
			startSpringServer(args);

			getStartupFrame().setVisible(false);
			getStartupFrame().dispose();
		});
	}
	
}
