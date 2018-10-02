package org.artorg.tools.phantomData.server.boot;

import java.io.File;

import huma.io.ConsoleDiverter;

public interface IBooter {

	void boot(String[] args);
	
	boolean isRunnableJarExecution();
	
	File getRunnableJarExecutionDirectory();
	
	void setConsoleFrameVisible(boolean b);
	
	void setStartupFrameVisible(boolean b);
	
	ConsoleDiverter getConsoleDiverter();
	
	Class<?> getBootApplicationClass();
	
	void setBootApplicationClass(Class<?> bootApplicationClass);

	void setConsoleDiverter(ConsoleDiverter consoleDiverter);

	boolean isErrorOccured();

	void setErrorOccured(boolean errorOccured);
	
	ConsoleFrame getConsoleFrame();

	void setConsoleFrame(ConsoleFrame consoleFrame);

	StartupProgressFrame getStartupFrame();
	
	void setStartupFrame(StartupProgressFrame startupFrame);

}
