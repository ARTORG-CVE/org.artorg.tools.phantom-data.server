package org.artorg.tools.phantomData.server;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.artorg.tools.phantomData.server.boot.SwingConsoleFrame;
import org.artorg.tools.phantomData.server.boot.SwingConsoleStartupServerBooter;
import org.artorg.tools.phantomData.server.boot.SwingStartupProgressFrame;
import org.artorg.tools.phantomData.server.model.base.DbFile;

import huma.io.ConsoleDiverter;

public class DesktopSwingBootServer extends SwingConsoleStartupServerBooter {
	
	public static void main(String[] args) {
		new DesktopSwingBootServer().boot(args);
	}
	
	public void boot(String[] args) {
		setBootApplicationClass(BootApplication.class);
		setExternalConfigOverridable(false);
		setConsoleDiverter(new ConsoleDiverter());
		setConsoleFrame(new SwingConsoleFrame());
		catchedBoot(() -> {
			setStartupFrame(new SwingStartupProgressFrame());
			init();
			
			getConsoleFrame().setVisible(true);
			
			
			prepareFileStructure();
			DbFile.setFilesPath(getFilesPath());
			
			if (isDebugConsoleMode())
				getConsoleFrame().setVisible(true);
			if (!isConnected()) {
				
				getStartupFrame().setnConsoleLines(221);
				getStartupFrame().setTitle("Phantom Database");
				getStartupFrame().setVisible(true);
				getStartupFrame().setProgressing(true);
				startSpringServer(args);
//				getStartupFrame().setVisible(false);
			} else {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Server already started!");
				frame.dispose();
			}
//			getStartupFrame().dispose();
		});
	}

}
