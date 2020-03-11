package com.z0nghe.multiple.mongodb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration  //Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
public class SecondaryMongoConfig extends AbstractMongoConfig{
    @Override
    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}
