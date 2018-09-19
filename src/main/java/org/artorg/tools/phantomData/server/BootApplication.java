package org.artorg.tools.phantomData.server;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
	
	public static void main(String[] args) {
		
		
		new DesktopSwingBootServer().boot(args);
	}

}
