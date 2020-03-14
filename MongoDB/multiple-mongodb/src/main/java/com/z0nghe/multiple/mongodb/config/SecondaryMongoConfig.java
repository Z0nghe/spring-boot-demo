package com.z0nghe.multiple.mongodb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
@EnableMongoRepositories(basePackages = "com.z0nghe.multiple.mongodb.repository.secondary",
        mongoTemplateRef = SecondaryMongoConfig.templateName)
public class SecondaryMongoConfig extends AbstractMongoConfig {
    public final static String templateName = "secondaryMongoTemplate";

    @Override
    @Bean(name = templateName)
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}
