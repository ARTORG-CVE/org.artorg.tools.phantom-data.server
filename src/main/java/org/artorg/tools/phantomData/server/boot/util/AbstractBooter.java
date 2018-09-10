package org.artorg.tools.phantomData.server.boot.util;

import org.artorg.tools.phantomData.server.boot.launchers.DesktopSwingLauncher;

public abstract class AbstractBooter {
	protected final LaunchConfigurationServer CONFIG;
	protected final DesktopSwingLauncher LAUNCHER;
	
	{
		CONFIG = new LaunchConfigurationServer();
		LAUNCHER = new DesktopSwingLauncher();
	}
	
	public abstract boolean boot(String[] args);
	
	public LaunchConfigurationServer getConfig() {
		return CONFIG;
	}

	public DesktopSwingLauncher getLauncher() {
		return LAUNCHER;
	}

}
