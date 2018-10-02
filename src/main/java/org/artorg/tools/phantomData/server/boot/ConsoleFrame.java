package org.artorg.tools.phantomData.server.boot;

import huma.io.ConsoleDiverter;

public abstract class ConsoleFrame {
	private ConsoleDiverter consoleDiverter;
	private boolean errorOccured;
	
	public abstract void setVisible(boolean visible);

	public abstract void setTitle(String string);

	public abstract Object getGraphic();
	
	public boolean isErrorOccured() {
		return errorOccured;
	}
	
	protected void setErrorOccured(boolean b) {
		errorOccured = b;
	}
	
	public void setConsoleDiverter(ConsoleDiverter consoleDiverter) {
		this.consoleDiverter = consoleDiverter;
	}
	
	public ConsoleDiverter getConsoleDiverter() {
		return consoleDiverter;
	}
	
	

}
