package com.z0nghe.multiple.mongodb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.primary")
@Slf4j
@EnableMongoRepositories(basePackages = "com.z0nghe.multiple.mongodb.repository.primary",
        mongoTemplateRef = PrimaryMongoConfig.templateName)
public class PrimaryMongoConfig extends AbstractMongoConfig {

    public final static String templateName = "primaryMongoTemplate";

    @Primary
    @Override
    @Bean(name = templateName)
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}
