package org.artorg.tools.phantomData.server.boot;

public abstract class ServerBooter {
	
	public abstract void boot(String[] args);
	
	public abstract LaunchConfigurationServer getServerConfig();
	
	public abstract void setStartupFrameVisible(boolean b);
	
	public abstract void setConsoleFrameVisible(boolean b);

}
