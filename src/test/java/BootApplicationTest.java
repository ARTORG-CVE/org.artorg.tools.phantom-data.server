import static org.artorg.tools.phantomData.server.boot.BootUtils.deleteDatabase;
import static org.artorg.tools.phantomData.server.boot.BootUtils.deleteFileStructure;
import static org.artorg.tools.phantomData.server.boot.BootUtils.logInfos;
import static org.artorg.tools.phantomData.server.boot.BootUtils.prepareFileStructure;
import static org.artorg.tools.phantomData.server.boot.BootUtils.shutdownServer;
import static org.artorg.tools.phantomData.server.boot.BootUtils.startingServer;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplicationTest {
	
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
        
	}

}
