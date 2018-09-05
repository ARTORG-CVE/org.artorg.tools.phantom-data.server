import org.artorg.tools.phantomData.server.boot.ServerLauncher;
import org.artorg.tools.phantomData.server.boot.LaunchConfigurationsServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplicationTest {
	
	public static void main(String[] args) {
		new ServerLauncher().launch(BootApplicationTest.class, LaunchConfigurationsServer.SWING_BOOT_TEST, args);
		
	}

}
