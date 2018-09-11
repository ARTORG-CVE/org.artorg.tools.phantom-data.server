package org.artorg.tools.phantomData.server;

import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.isRunnableJarExecution;
import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.startingServer;

import org.artorg.tools.phantomData.server.boot.BootApplication;
import org.artorg.tools.phantomData.server.boot.BootUtilsServer;
import org.artorg.tools.phantomData.server.boot.ConsoleDiverter;
import org.artorg.tools.phantomData.server.boot.LaunchConfigurationServer;
import org.artorg.tools.phantomData.server.boot.ServerBooter;
import org.artorg.tools.phantomData.server.boot.SwingConsoleFrame;
import org.artorg.tools.phantomData.server.boot.SwingStartupProgressFrame;

public class DesktopSwingBootServer extends ServerBooter {
	private final LaunchConfigurationServer config;
	private final ConsoleDiverter consoleDiverter;
	private final SwingConsoleFrame consoleFrame;
	private final SwingStartupProgressFrame startupFrame;
	private final boolean externalConfigOverridable = false;
	private final boolean showConsoleFrameOnStartup = false;
	private boolean errorOccured;
	
	{
		config = new LaunchConfigurationServer(BootApplication.class, externalConfigOverridable);
		consoleDiverter = new ConsoleDiverter();
		consoleFrame = new SwingConsoleFrame(consoleDiverter);
		startupFrame = new SwingStartupProgressFrame(consoleDiverter);
		
		
	}
	
	public static void main(String[] args) {
		new DesktopSwingBootServer().boot(args);
	}
	
	public void boot(String[] args) {
		try {
			if (showConsoleFrameOnStartup)
				consoleFrame.setVisible(true);
			if (!BootUtilsServer.isConnected(config))
				startupFrame.setVisible(true);
			startupFrame.setnConsoleLines(191);
			startupFrame.startProgress();
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
