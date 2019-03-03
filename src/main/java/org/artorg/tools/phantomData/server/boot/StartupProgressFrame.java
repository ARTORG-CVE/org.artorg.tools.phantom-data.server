package org.artorg.tools.phantomData.server.boot;

import huma.io.ConsoleDiverter;

/**
 * An abstract graphical startup frame with progress. Can be used for showing
 * boot procedure.
 * 
 * @see ConsoleDiverter
 */
public abstract class StartupProgressFrame {
	private ConsoleDiverter consoleDiverter;
	private double progress;
	private int nConsoleLines;
	private boolean progressing;
	
	{
		progress = 0.0;
	}
	
	/**
	 * @param b Visibility. {@code true} for showing the frame graphically.
	 */
	public abstract void setVisible(boolean b);
	
	/**
	 * Destroys the frame. Not every implementation of this class needs does need
	 * this.
	 */
	public abstract void dispose();
	
	/**
	 * A title that is visible in the task bar during start procedure. Depending on
	 * implementation the title will be shown on the frame but must not.
	 * 
	 * @param title Title of frame.
	 */
	public abstract void setTitle(String title);
	
	/**
	 * @param consoleDiverter For redirecting the console output into the startup
	 *                        frame for progressing during start procedure.
	 */
	public void setConsoleDiverter(ConsoleDiverter consoleDiverter) {
		this.consoleDiverter = consoleDiverter;
	}
	
	/**
	 * @return For redirecting the console output into the startup frame for
	 *         progressing during start procedure.
	 */
	public ConsoleDiverter getConsoleDiverter() {
		return consoleDiverter;
	}
	
	/**
	 * @return Instance of the frame. E.g. Stage, Window, JFrame, ...
	 */
	public abstract Object getGraphic();
	
	/**
	 * @return Progress of start procedure connected by number of lines in console
	 *         output.
	 *         <p>
	 *         {@code 0.0 <= p <= 1.0}
	 * @see #getnConsoleLines()
	 */
	public double getProgress() {
		return progress;
	}
	
	/**
	 * @param progress Progress of start procedure connected by number of lines in
	 *                 console output.
	 *                 <p>
	 *                 {@code 0.0 <= p <= 1.0}
	 * @see #getnConsoleLines()
	 */
	public void setProgress(double progress) {
		this.progress = progress;
	}
	
	/**
	 * @return Number of lines that start procedure will print in console output.
	 */
	public int getnConsoleLines() {
		return nConsoleLines;
	}
	
	/**
	 * @param nConsoleLines Number of lines that start procedure will print in
	 *                      console output.
	 */
	public void setnConsoleLines(int nConsoleLines) {
		this.nConsoleLines = nConsoleLines;
	}
	
	/**
	 * @param b Progress will start updating by console output. Can be useful to
	 *          hide some of the start output and start at a specific time. Has to
	 *          be set {@code true} for showing the progressing.
	 */
	public void setProgressing(boolean b) {
		progressing = b;
	}
	
	/**
	 * @return {@code true} when progressing is started. If console output is not
	 *         correctly redirected to this, {@code true} will be returned but
	 *         progress will not shown correctly.
	 */
	public boolean isProgressing() {
		return progressing;
	}
	
}
