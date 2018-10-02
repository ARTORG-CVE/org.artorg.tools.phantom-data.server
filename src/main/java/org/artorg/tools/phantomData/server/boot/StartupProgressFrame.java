package org.artorg.tools.phantomData.server.boot;

import huma.io.ConsoleDiverter;

public abstract class StartupProgressFrame {
	private ConsoleDiverter consoleDiverter;
	private double progress;
	private int nConsoleLines;
	private boolean progressing;

	{
		progress = 0.0;
	}
	
	public abstract void setVisible(boolean b);
	
	public abstract void dispose();
	
	public abstract void setTitle(String title);

	public void setConsoleDiverter(ConsoleDiverter consoleDiverter) {
		this.consoleDiverter = consoleDiverter;
	}
	
	public ConsoleDiverter getConsoleDiverter() {
		return consoleDiverter;
	}
	
	public abstract Object getGraphic();
	
	public double getProgress() {
		return progress;
	}
	
	public void setProgress(double progress) {
		this.progress = progress;
	}
	
	public int getnConsoleLines() {
		return nConsoleLines;
	}

	public void setnConsoleLines(int nConsoleLines) {
		this.nConsoleLines = nConsoleLines;
	}
	
	public void setProgressing(boolean b) {
		progressing = b;
	}
	
	public boolean isProgressing() {
		return progressing;
	}

}
