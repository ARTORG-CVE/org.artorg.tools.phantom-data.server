package org.artorg.tools.phantomData.server.boot;

import java.io.File;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import huma.io.ConsoleDiverter;

public abstract class Booter implements IBooter {
	private static final Pattern runnableJarPattern = Pattern.compile("jar:file:/(.*)[\\u002e]jar");
	private ConsoleDiverter consoleDiverter;
	private ConsoleFrame consoleFrame;
	private StartupProgressFrame startupFrame;
	private boolean errorOccured;
	private Class<?> bootApplicationClass;

	public abstract void boot(String[] args);
	
	public boolean isRunnableJarExecution() {
		String uriPath = null;
		try {
			uriPath = getBootApplicationClass().getProtectionDomain().getCodeSource().getLocation().toURI().toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		if (uriPath.contains(".jar")) {
			Matcher m = runnableJarPattern.matcher(uriPath);
			return m.find();
		}
		return false;
	}
	
	public File getRunnableJarExecutionDirectory() {
		String path = null;
		
		try {
			path = getBootApplicationClass().getProtectionDomain().getCodeSource().getLocation().toURI().toString();
		} catch (URISyntaxException e2) {
			e2.printStackTrace();
		}
		
		Pattern pattern = Pattern.compile("jar:file:/(.*)");
		Matcher m = pattern.matcher(path);
		File file;
		if(m.find())
			file = new File(m.group(1));
		else 
			throw new IllegalArgumentException();
		
		while (file.getPath().contains(".jar"))
			file = file.getParentFile();

		return file;
	}
	
	public void setConsoleFrameVisible(boolean b) {
		consoleFrame.setVisible(b);
	}
	
	public void setStartupFrameVisible(boolean b) {
		startupFrame.setVisible(b);
	}
	
	public ConsoleDiverter getConsoleDiverter() {
		return consoleDiverter;
	}
	
	public Class<?> getBootApplicationClass() {
		return bootApplicationClass;
	}
	
	public void setBootApplicationClass(Class<?> bootApplicationClass) {
		this.bootApplicationClass = bootApplicationClass;
	}

	public void setConsoleDiverter(ConsoleDiverter consoleDiverter) {
		this.consoleDiverter = consoleDiverter;
		if (consoleFrame != null)
			consoleFrame.setConsoleDiverter(consoleDiverter);
		if (startupFrame != null)
			startupFrame.setConsoleDiverter(consoleDiverter);
	}

	public boolean isErrorOccured() {
		return errorOccured;
	}

	public void setErrorOccured(boolean errorOccured) {
		this.errorOccured = errorOccured;
	}
	
	public ConsoleFrame getConsoleFrame() {
		return consoleFrame;
	}

	public void setConsoleFrame(ConsoleFrame consoleFrame) {
		this.consoleFrame = consoleFrame;
		if (consoleDiverter != null)
			consoleFrame.setConsoleDiverter(consoleDiverter);
	}

	public StartupProgressFrame getStartupFrame() {
		return startupFrame;
	}

	public void setStartupFrame(StartupProgressFrame startupFrame) {
		this.startupFrame = startupFrame;
		if (startupFrame != null)
			startupFrame.setConsoleDiverter(consoleDiverter);
	}
	
}