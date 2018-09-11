package org.artorg.tools.phantomData.server;

import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.deleteDatabase;
import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.deleteFileStructure;
import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.isRunnableJarExecution;
import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.prepareFileStructure;
import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.shutdownServer;
import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.startingServer;

import org.artorg.tools.phantomData.server.boot.BootUtilsServer;
import org.artorg.tools.phantomData.server.boot.ConsoleDiverter;
import org.artorg.tools.phantomData.server.boot.LaunchConfigurationServer;
import org.artorg.tools.phantomData.server.boot.ServerBooter;
import org.artorg.tools.phantomData.server.boot.SwingConsoleFrame;
import org.artorg.tools.phantomData.server.boot.SwingStartupProgressFrame;

public class DesktopSwingResetBootServer extends ServerBooter {
	private final LaunchConfigurationServer config;
	private final ConsoleDiverter consoleDiverter;
	private final SwingConsoleFrame consoleFrame;
	private final SwingStartupProgressFrame startupFrame;
	private final boolean externalConfigOverridable = true;
	private final boolean showConsoleFrameOnStartup = false;
	private boolean errorOccured;
	
	{
		config = new LaunchConfigurationServer(BootApplication.class, externalConfigOverridable);
		consoleDiverter = new ConsoleDiverter();
		consoleFrame = new SwingConsoleFrame(consoleDiverter);
		startupFrame = new SwingStartupProgressFrame(consoleDiverter);
		startupFrame.setnConsoleLines(191);
		
		
	}
	
	public static void main(String[] args) {
		new DesktopSwingResetBootServer().boot(args);
	}

	public void boot(String[] args) {
		try {
			if (showConsoleFrameOnStartup)
				consoleFrame.setVisible(true);
			shutdownServer(config);
			deleteDatabase(config);
			deleteFileStructure(config);
			prepareFileStructure(config);
			// logInfos();
			startupFrame.setnConsoleLines(191);
			startupFrame.startProgress();
			if (!BootUtilsServer.isConnected(config))
				startupFrame.setVisible(true);
			startingServer(config, args);

			startupFrame.setVisible(false);
			startupFrame.dispose();
		} catch (Exception e) {
			consoleFrame.setTitle("Phantom Database - Exception thrown!");
			e.printStackTrace();
			errorOccured = true;
		}
		if (!consoleFrame.isErrorOccured() && !errorOccured)
			consoleFrame.setVisible(false);
		else if (isRunnableJarExecution(config.getBootApplicationClass())) 
			consoleFrame.setVisible(true);
	}

	@Override
	public LaunchConfigurationServer getServerConfig() {
		return config;
	}

	@Override
	public void setStartupFrameVisible(boolean b) {
		startupFrame.setVisible(b);
	}

	@Override
	public void setConsoleFrameVisible(boolean b) {
		consoleFrame.setVisible(b);
	}
	
}
