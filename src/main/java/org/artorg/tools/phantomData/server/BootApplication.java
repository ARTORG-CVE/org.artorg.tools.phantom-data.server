package org.artorg.tools.phantomData.server;

import static org.artorg.tools.phantomData.server.boot.BootUtils.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		prepareFileStructure();
		logInfos();
		startingServer(args);
		
	}

}
