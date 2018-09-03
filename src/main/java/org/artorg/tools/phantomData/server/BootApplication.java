package org.artorg.tools.phantomData.server;

import static org.artorg.tools.phantomData.server.boot.BootUtils.isConnected;
import static org.artorg.tools.phantomData.server.boot.BootUtils.logInfos;
import static org.artorg.tools.phantomData.server.boot.BootUtils.init;
import static org.artorg.tools.phantomData.server.boot.BootUtils.prepareFileStructure;
import static org.artorg.tools.phantomData.server.boot.BootUtils.startingServer;

import org.artorg.tools.phantomData.server.boot.Launcher;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		launcher.launch(192, () -> {
			init();
			prepareFileStructure();
			logInfos();
			
			new Thread(() -> startingServer(args)).start();
				
			while(!isConnected()) {
				try {Thread.sleep(1000);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
			
		});
		
	}

}
