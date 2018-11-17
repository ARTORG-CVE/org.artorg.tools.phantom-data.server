package org.artorg.tools.phantomData.server.boot;

import huma.io.ConsoleDiverter;

public interface ConsoleFrame {
	
	void setVisible(boolean visible);

	void setTitle(String string);

	Object getGraphic();
	
	boolean isErrorOccured();
	
	void setErrorOccured(boolean b);
	
	void setConsoleDiverter(ConsoleDiverter consoleDiverter);
	
	ConsoleDiverter getConsoleDiverter();
	
}
