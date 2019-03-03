package org.artorg.tools.phantomData.server.boot;

import huma.io.ConsoleDiverter;

/**
 * A graphical logging pane that shows the command line output.
 * <p>
 * Note: If some lines are logged in static initializer they will not be shown
 * in the panel.
 */
public interface ConsoleFrame {
	
	/**
	 * Set the visibility of frame.
	 * 
	 * @param visible Visibility
	 */
	void setVisible(boolean visible);
	
	/**
	 * The visible title of the frame.
	 * 
	 * @param string Title
	 */
	void setTitle(String string);
	
	/**
	 * @return Instance of the frame.
	 */
	Object getGraphic();
	
	boolean isErrorOccured();
	
	void setErrorOccured(boolean b);
	
	void setConsoleDiverter(ConsoleDiverter consoleDiverter);
	
	ConsoleDiverter getConsoleDiverter();
	
}
