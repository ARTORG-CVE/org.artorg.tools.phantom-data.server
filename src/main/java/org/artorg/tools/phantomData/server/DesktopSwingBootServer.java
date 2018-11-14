package org.artorg.tools.phantomData.server;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.artorg.tools.phantomData.server.boot.ServerBooter;
import org.artorg.tools.phantomData.server.boot.SwingConsoleFrame;
import org.artorg.tools.phantomData.server.boot.SwingStartupProgressFrame;
import org.artorg.tools.phantomData.server.model.base.DbFile;

import huma.io.ConsoleDiverter;

public class DesktopSwingBootServer extends ServerBooter {

	public static void main(String[] args) {
		new DesktopSwingBootServer().boot(args);
	}

	public void boot(String[] args) {
		try {
			setBootApplicationClass(BootApplication.class);
			setExternalConfigOverridable(false);
			setConsoleDiverter(new ConsoleDiverter());
			setConsoleFrame(new SwingConsoleFrame());
			setStartupFrame(new SwingStartupProgressFrame());
			init();
			prepareFileStructure();
			DbFile.setFilesPath(getFilesPath());

			if (isDebugConsoleMode()) getConsoleFrame().setVisible(true);
			if (!isConnected()) {
				getStartupFrame().setnConsoleLines(39);
				getStartupFrame().setTitle("Phantom Database");
				getStartupFrame().setVisible(true);
				getStartupFrame().setProgressing(true);
				startSpringServer(args);
				getStartupFrame().setVisible(false);
			} else {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Server already started!");
				frame.dispose();
				System.exit(0);
			}
			getStartupFrame().dispose();
		} catch (Exception e) {
			getConsoleFrame().setTitle("Phantom Database - Exception thrown!");
			setErrorOccured(true);
			if (!super.handleException(e)) e.printStackTrace();
		}
		if (!getConsoleFrame().isErrorOccured() && !isErrorOccured()
			&& !isDebugConsoleMode()) getConsoleFrame().setVisible(false);
		else if (isRunnableJarExecution()) getConsoleFrame().setVisible(true);
	}

}
