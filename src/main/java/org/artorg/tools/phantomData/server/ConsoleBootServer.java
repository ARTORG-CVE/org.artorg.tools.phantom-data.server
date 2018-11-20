package org.artorg.tools.phantomData.server;

import org.artorg.tools.phantomData.server.boot.ServerBooter;
import org.artorg.tools.phantomData.server.model.base.DbFile;

public class ConsoleBootServer extends ServerBooter {

	public static void main(String[] args) {
		new ConsoleBootServer().uncatchedBoot(args);
	}

	@Override
	public void uncatchedBoot(String[] args) {
		setBootApplicationClass(BootApplication.class);
		setExternalConfigOverridable(false);

		init();
		prepareFileStructure();
		DbFile.setFilesPath(getFilesPath());

		if (!isConnected()) {
			startSpringServer(args);
		} else {
			System.out.println("Server already started!");
			System.exit(0);
		}
	}

}
