package io.marketmovers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by mikechabot on 10/28/15.
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "io.marketmovers")
@PropertySource("classpath:bootstrap.properties")
public class AppConfig {

}
