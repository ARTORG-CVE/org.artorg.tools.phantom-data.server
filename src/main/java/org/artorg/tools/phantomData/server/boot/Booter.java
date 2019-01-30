package org.artorg.tools.phantomData.server.boot;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import huma.io.ConsoleDiverter;

/**
 * Base class for booting with startup and console frame. Implement
 * {@code uncatchedBott} and call {@code catchedBoot} for starting.
 */
public abstract class Booter {
	/**
	 * Simple pattern for proof whether the application is started from a jar. Does
	 * not work if the application is wrapped in an executable.
	 */
	private static final Pattern runnableJarPattern = Pattern
			.compile("jar:file:/(.*)[\\u002e]jar");
	private ConsoleDiverter consoleDiverter;
	private ConsoleFrame consoleFrame;
	private StartupProgressFrame startupFrame;
	private boolean errorOccured;
	private Class<?> bootApplicationClass;
	private boolean debugConsoleMode;
	
	{
		debugConsoleMode = true;
		errorOccured = false;
	}
	
	/**
	 * Main boot method. It's called from {@code catchedBoot}. Call catchedBoot with
	 * command line arguments for starting successful.
	 * 
	 * @param args Command line arguments
	 * @see catchedBoot
	 */
	protected abstract void uncatchedBoot(String[] args);
	
	/**
	 * @return {@code true} if the application is running on a windows machine
	 */
	public final boolean isWindowsOs() {
		return System.getProperty("os.name").matches("(?i).*windows.*");
	}
	
	/**
	 * @return {@code true} if the application is running on a linux machine
	 */
	public final boolean isLinuxOs() {
		return System.getProperty("os.name").matches("(?i).*linux.*");
	}
	
	/**
	 * @return {@code true} if the application is running on a macintosh machine
	 */
	public final boolean isMacOs() {
		return System.getProperty("os.name").matches("(?i).*mac.*");
	}
	
	/**
	 * Starts the booting process and handles exceptions with the console frame.
	 * @param args Command line arguments
	 */
	public final void catchedBoot(String[] args) {
		try {
			uncatchedBoot(args);
		} catch (Exception e) {
			handleException(e);
		}
		if (!getConsoleFrame().isErrorOccured() && !isErrorOccured()
				&& !isDebugConsoleMode())
			getConsoleFrame().setVisible(false);
		else getConsoleFrame().setVisible(true);
	}
	
	public void handleException(Exception e) {
		e.printStackTrace();
		getConsoleFrame().setTitle("Phantom Database - Exception thrown!");
		setErrorOccured(true);
		getConsoleFrame().setVisible(true);
	}
	
	public boolean isRunnableJarExecution() {
		try {
			String uriPath = getBootApplicationClass().getProtectionDomain()
					.getCodeSource().getLocation().toURI().toString();
			if (uriPath.contains(".jar")) {
				Matcher m = runnableJarPattern.matcher(uriPath);
				return m.find();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public File getRunnableJarExecutionDirectory() {
		try {
			String path = getBootApplicationClass().getProtectionDomain().getCodeSource()
					.getLocation().toURI().toString();
			Pattern pattern = Pattern.compile("jar:file:(.*)");
			Matcher m = pattern.matcher(path);
			File file;
			if (m.find()) file = new File(m.group(1));
			else throw new IllegalArgumentException();
			
			while (file.getPath().contains(".jar"))
				file = file.getParentFile();
			
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void setConsoleDiverter(ConsoleDiverter consoleDiverter) {
		this.consoleDiverter = consoleDiverter;
		if (consoleFrame != null) consoleFrame.setConsoleDiverter(consoleDiverter);
		if (startupFrame != null) startupFrame.setConsoleDiverter(consoleDiverter);
	}
	
	public void setConsoleFrame(ConsoleFrame consoleFrame) {
		this.consoleFrame = consoleFrame;
		if (consoleDiverter != null) consoleFrame.setConsoleDiverter(consoleDiverter);
	}
	
	public StartupProgressFrame getStartupFrame() {
		return startupFrame;
	}
	
	public void setStartupFrame(StartupProgressFrame startupFrame) {
		this.startupFrame = startupFrame;
		if (startupFrame != null) startupFrame.setConsoleDiverter(consoleDiverter);
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
	
	public boolean isDebugConsoleMode() {
		return debugConsoleMode;
	}
	
	public void setDebugConsoleMode(boolean debugConsoleMode) {
		this.debugConsoleMode = debugConsoleMode;
	}
	
}
