package com.z0nghe.multiple.mongodb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.z0nghe.multiple.mongodb.repository.primary",
        mongoTemplateRef = PrimaryMongoConfig.MONGO_TEMPLATE)
public class PrimaryMongoConfig {
    protected static final String MONGO_TEMPLATE = "primaryMongoTemplate";
}