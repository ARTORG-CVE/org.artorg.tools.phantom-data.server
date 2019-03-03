package org.artorg.tools.phantomData.server.boot;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import huma.io.ConsoleDiverter;

/**
 * Base class for booting with startup and console frame. Implement
 * {@link #unsecuredBoot(String[])} and call {@link #securedBoot(String[])} for
 * starting.
 * <p>
 * Set a {@link StartupProgressFrame} for visualize the boot procedure.
 * <p>
 * Set a {@link ConsoleFrame} and a {@link ConsoleDiverter} for showing a
 * logging panel during boot procedure. It will shown if an error occurs or if
 * debug mode is enabled.
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
	private boolean errorOccured = false;
	private Class<?> bootApplicationClass;
	
	/**
	 * Main boot method. It's called from {@link #securedBoot(String[])}. Call
	 * catchedBoot with command line arguments for starting successful.
	 * 
	 * @param args Command line arguments
	 */
	protected abstract void unsecuredBoot(String[] args);
	
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
	 * 
	 * @param args Command line arguments
	 */
	public final void securedBoot(String[] args) {
		try {
			unsecuredBoot(args);
		} catch (Exception e) {
			errorOccured = true;
			handleException(e);
		}
		if (!getConsoleFrame().isErrorOccured() && !isErrorOccured())
			getConsoleFrame().setVisible(false);
		else getConsoleFrame().setVisible(true);
	}
	
	/**
	 * Override this method for handling the exception occurred during catchedBoot.
	 * 
	 * @param e Thrown exception during boot process.
	 */
	public void handleException(Exception e) {
		e.printStackTrace();
		setErrorOccured(true);
		if (getConsoleFrame() != null) {
			getConsoleFrame().setTitle("Phantom Database - Exception thrown!");
			getConsoleFrame().setVisible(true);
		}
	}
	
	/**
	 * NOTE: This method does not work when the *.jar is wrapped in other
	 * executables.
	 * 
	 * @return {@code true} if the application is started from a *.jar file.
	 */
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
	
	/**
	 * @return Directory where the jar is running from.
	 */
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
	
	/**
	 * 
	 * @param consoleDiverter An implementation for showing command line output.
	 * @throws NullPointerException if consoleDiverter is null.
	 */
	public void setConsoleDiverter(ConsoleDiverter consoleDiverter) {
		if (consoleDiverter == null) throw new NullPointerException();
		this.consoleDiverter = consoleDiverter;
		if (consoleFrame != null) consoleFrame.setConsoleDiverter(consoleDiverter);
		if (startupFrame != null) startupFrame.setConsoleDiverter(consoleDiverter);
	}
	
	/**
	 * @param consoleFrame An implementation or {@code null}.
	 */
	public void setConsoleFrame(ConsoleFrame consoleFrame) {
		this.consoleFrame = consoleFrame;
		if (consoleFrame != null) consoleFrame.setConsoleDiverter(consoleDiverter);
	}
	
	/**
	 * @return An implementation or {@code null}.
	 */
	public StartupProgressFrame getStartupFrame() {
		return startupFrame;
	}
	
	/**
	 * @param startupFrame An implementation or {@code null}.
	 */
	public void setStartupFrame(StartupProgressFrame startupFrame) {
		this.startupFrame = startupFrame;
		if (startupFrame != null) startupFrame.setConsoleDiverter(consoleDiverter);
	}
	
	/**
	 * @return {@code true} if an error occurred during secure boot process.
	 */
	public boolean isErrorOccured() {
		return errorOccured;
	}
	
	/**
	 * Setting an switch so that the boot procedure can abort or handle it. Can be
	 * used for showing the {@link ConsoleFrame}.
	 * 
	 * @param errorOccured {@code true} if an error is occurred.
	 */
	public void setErrorOccured(boolean errorOccured) {
		this.errorOccured = errorOccured;
	}
	
	/**
	 * @return An graphical command line frame with the output or {@code null}.
	 */
	public ConsoleFrame getConsoleFrame() {
		return consoleFrame;
	}
	
	/**
	 * @return An instance or {@code null}.
	 */
	public ConsoleDiverter getConsoleDiverter() {
		return consoleDiverter;
	}
	
	/**
	 * @return The Spring Boot application class annotated with
	 *         {@link org.springframework.boot.autoconfigure.SpringBootApplication}.
	 */
	public Class<?> getBootApplicationClass() {
		return bootApplicationClass;
	}
	
	/**
	 * @param bootApplicationClass The Spring Boot application class annotated with
	 *                             {@link org.springframework.boot.autoconfigure.SpringBootApplication}.
	 */
	public void setBootApplicationClass(Class<?> bootApplicationClass) {
		this.bootApplicationClass = bootApplicationClass;
	}
	
}
