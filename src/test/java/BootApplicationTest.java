import org.artorg.tools.phantomData.server.boot.DesktopSwingBoot;
import org.artorg.tools.phantomData.server.boot.DesktopSwingResetBoot;
import org.junit.Test;

public class BootApplicationTest {
	
	@Test
	public void test() {
		new DesktopSwingBoot().boot(new String[] {});
		new DesktopSwingResetBoot().boot(new String[] {});
	}

}
