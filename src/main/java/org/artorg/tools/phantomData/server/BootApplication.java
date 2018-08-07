package org.artorg.tools.phantomData.server;

import static org.artorg.tools.phantomData.server.boot.BootUtils.*;
import static org.artorg.tools.phantomData.server.boot.BootInit.*;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		startingServerDev(args);
		
	}
	
	public static void startingServerDev(String[] args) {
		shutdownServer();
		deleteDatabase();
		deleteFileStructure();
		prepareFileStructure();
		logInfos();
		startingServer(args);
		
		initDatabase();
        
	}
	
}
