package org.artorg.tools.phantomData.server;

import org.artorg.tools.phantomData.server.boot.SwingConsoleFrame;
import org.artorg.tools.phantomData.server.boot.SwingConsoleStartupServerBooter;
import org.artorg.tools.phantomData.server.boot.SwingStartupProgressFrame;
import org.artorg.tools.phantomData.server.model.DbFile;

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
			DbFile.setFilesPath(getFilesPath());
			
			shutdownSpringServer();
			deleteFileStructure();
			
			init();
			prepareFileStructure();
			
			if (!isConnected()) {
				getStartupFrame().setnConsoleLines(191);
				getStartupFrame().setTitle("Phantom Database");
				getStartupFrame().setVisible(true);
				getStartupFrame().setProgressing(true);
			}
			startSpringServer(args);

			getStartupFrame().setVisible(false);
			getStartupFrame().dispose();
		});
	}
	
}
