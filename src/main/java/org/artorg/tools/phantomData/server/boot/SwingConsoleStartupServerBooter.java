package org.artorg.tools.phantomData.server.boot;

public abstract class SwingConsoleStartupServerBooter extends ServerBooter {
	
	protected boolean catchedBoot(Runnable rc) {
		try {
			rc.run();
		} catch (Exception e) {
			getConsoleFrame().setTitle("Phantom Database - Exception thrown!");
			setErrorOccured(true);
			if (!super.handleException(e))
				e.printStackTrace();
		}
		if (!getConsoleFrame().isErrorOccured() && !isErrorOccured() && !isDebugConsoleMode())
			;
//			getConsoleFrame().setVisible(false);
		else if (isRunnableJarExecution()) 
			getConsoleFrame().setVisible(true);
		if (isErrorOccured())
			return false;
		return true;
	}
	
}