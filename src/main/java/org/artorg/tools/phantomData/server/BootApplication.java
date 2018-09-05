package org.artorg.tools.phantomData.server;

import org.artorg.tools.phantomData.server.boot.ServerLauncher;
import org.artorg.tools.phantomData.server.boot.LaunchConfigurationsServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

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
		
		
		new ServerLauncher().launch(BootApplication.class, LaunchConfigurationsServer.SWING_BOOT_AUTO, args);
		
	}

}
