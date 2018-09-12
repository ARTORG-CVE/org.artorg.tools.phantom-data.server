package org.artorg.tools.phantomData.server;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.artorg.tools.phantomData.server.boot.SwingConsoleStartupServerBooter;
import org.artorg.tools.phantomData.server.model.PhantomFile;

public class DesktopSwingBootServer extends SwingConsoleStartupServerBooter {
	
	{
		setBootApplicationClass(BootApplication.class);
		setExternalConfigOverridable(false);
	}
	
	public static void main(String[] args) {
		new DesktopSwingBootServer().boot(args);
	}
	
	public void boot(String[] args) {
		catchedBoot(() -> {
			init();
			prepareFileStructure();
			PhantomFile.setFilesPath(getFilesPath());
			
			if (isDebugConsoleMode())
				consoleFrame.setVisible(true);
			if (!isConnected()) {
				startupFrame.setVisible(true);
				startupFrame.setnConsoleLines(191);
				startupFrame.startProgress();
				startSpringServer(args);
				startupFrame.setVisible(false);
			} else {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Server already started!");
				frame.dispose();
			}
			startupFrame.dispose();
		});
	}

}
