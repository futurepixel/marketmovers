package io.marketmovers.config;

import com.mongodb.MongoClient;
import io.marketmovers.RepositoryPackage;
import io.marketmovers.beans.MongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Created by mikechabot on 10/28/15.
 */
@Configuration
@Import(RepositoryRestMvcConfiguration.class)
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses=RepositoryPackage.class)
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private MongoProperties mongoProperties;

    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabaseName();
    }

    @Override
    @Bean
    public MongoClient mongo() throws Exception {
        MongoClient client = new MongoClient(mongoProperties.getHostName(), mongoProperties.getPortNumber());
        client.setWriteConcern(mongoProperties.getWriteConcern());
        return client;
    }

    @Override
    protected String getMappingBasePackage() {
        return mongoProperties.getModelBasePackage();
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }


}
