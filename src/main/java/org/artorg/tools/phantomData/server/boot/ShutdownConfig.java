package org.artorg.tools.phantomData.server.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShutdownConfig {
 
    @Bean
    public TerminateBean getTerminateBean() {
        return new TerminateBean();
    }
}