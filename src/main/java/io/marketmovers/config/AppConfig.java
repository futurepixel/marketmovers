package io.marketmovers.config;

import com.mongodb.WriteConcern;
import io.marketmovers.beans.MongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by mikechabot on 10/28/15.
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "io.marketmovers")
@PropertySource("classpath:bootstrap.properties")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public MongoProperties mongoProperties() {
        return new MongoProperties(
                environment.getRequiredProperty("mongo-db-name"),
                environment.getRequiredProperty("mongo-model-base-package"),
                environment.getRequiredProperty("mongo-host-name"),
                environment.getRequiredProperty("mongo-port-number", Integer.class),
                environment.getProperty("mongo-write-concern", WriteConcern.class, WriteConcern.SAFE)
        );
    }

}
