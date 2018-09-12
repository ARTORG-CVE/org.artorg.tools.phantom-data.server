package org.artorg.tools.phantomData.server.boot;

public abstract class SwingConsoleStartupServerBooter extends ServerBooter {
	private final ConsoleDiverter consoleDiverter;
	protected final SwingConsoleFrame consoleFrame;
	protected final SwingStartupProgressFrame startupFrame;
	private boolean errorOccured;
	
	{
		consoleDiverter = new ConsoleDiverter();
		consoleFrame = new SwingConsoleFrame(consoleDiverter);
		startupFrame = new SwingStartupProgressFrame(consoleDiverter);
		startupFrame.setnConsoleLines(191);
	}
	
	protected boolean catchedBoot(Runnable rc) {
		try {
			rc.run();
		} catch (Exception e) {
			consoleFrame.setTitle("Phantom Database - Exception thrown!");
			errorOccured = true;
			if (!super.handleException(e))
				e.printStackTrace();
		}
		if (!consoleFrame.isErrorOccured() && !errorOccured && !isDebugConsoleMode())
			consoleFrame.setVisible(false);
		else if (isRunnableJarExecution()) 
			consoleFrame.setVisible(true);
		if (errorOccured)
			return false;
		return true;
	}

	@Override
	public void setStartupFrameVisible(boolean b) {
		startupFrame.setVisible(b);
	}

	@Override
	public void setConsoleFrameVisible(boolean b) {
		consoleFrame.setVisible(b);
	}
	
}