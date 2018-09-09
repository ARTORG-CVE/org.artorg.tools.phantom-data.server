package org.artorg.tools.phantomData.server;

import org.artorg.tools.phantomData.server.boot.DesktopSwingLauncher;
import org.artorg.tools.phantomData.server.boot.LaunchConfigurationsServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
	
//	private static Logger logger = LoggerFactory.getLogger(BootApplication.class);
	
	public static void main(String[] args) {
		
//		
//		
//		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(BootApplication.class)
//                .properties("spring.config.name=application,myapp",
//                        "spring.config.location=classpath:/data/myapp/config,classpath:/data/myapp/external/config")
//                .build().run(args);
// 
//        ConfigurableEnvironment environment = applicationContext.getEnvironment();
// 
//        logger.info(environment.getProperty("cmdb.resource-url"));
		
//		org.artorg.tools.phantomData.server.boot.BootUtilsServer.startingServer(BootApplication.class, "8183", args);
		
		new DesktopSwingLauncher().launch(BootApplication.class, LaunchConfigurationsServer.DESKTOP_SWING_BOOT, args);
//		System.exit(0);
		
	}

}
