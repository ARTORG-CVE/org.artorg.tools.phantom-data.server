package org.artorg.tools.phantomData.server.boot;

import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.deleteDatabase;
import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.deleteFileStructure;
import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.isConnected;
import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.prepareFileStructure;
import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.shutdownServer;
import static org.artorg.tools.phantomData.server.boot.BootUtilsServer.startingServer;

import org.artorg.tools.phantomData.server.BootApplication;

public class LaunchConfigurationsServer {
	public static final LaunchConfigurationServer DESKTOP_SWING_BOOT;
//	public static final LaunchConfigurationServer SWING_BOOT_TEST;
	
	static {
		DESKTOP_SWING_BOOT = new LaunchConfigurationServer();
		DESKTOP_SWING_BOOT.setBootApplicationClass(BootApplication.class);
		DESKTOP_SWING_BOOT.setConsumer(args -> {
//			prepareFileStructure(DESKTOP_SWING_BOOT);
			
			new Thread(() -> startingServer(DESKTOP_SWING_BOOT, args)).start();
				
			while(!isConnected(DESKTOP_SWING_BOOT)) {
				try {Thread.sleep(1000);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
			
		});
		
//		SWING_BOOT_TEST = new LaunchConfigurationServer();
//		SWING_BOOT_TEST.setBootApplicationClass(BootApplication.class);
//		SWING_BOOT_TEST.setConsumer(args -> {
//			shutdownServer(SWING_BOOT_TEST);
//			deleteDatabase(SWING_BOOT_TEST);
//			deleteFileStructure(SWING_BOOT_TEST);
//			prepareFileStructure(SWING_BOOT_TEST);
////			logInfos();
//			startingServer(SWING_BOOT_TEST, args);
//			
//		});
		

	}

}
