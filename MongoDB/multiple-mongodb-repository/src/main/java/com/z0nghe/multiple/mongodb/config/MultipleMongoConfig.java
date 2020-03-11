package com.z0nghe.multiple.mongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.z0nghe.multiple.mongodb.config.props.MultipleMongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MultipleMongoConfig {
    @Autowired
    private MultipleMongoProperties mongoProperties;

    @Primary
    @Bean
    @Qualifier(PrimaryMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
    }

    @Bean
    @Qualifier(SecondaryMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()));
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(MongoProperties mongo) {
        return getMongoDbFactory(mongo);
    }

    @Bean
    public MongoDbFactory secondaryFactory(MongoProperties mongo) {
        return getMongoDbFactory(mongo);
    }

    private MongoDbFactory getMongoDbFactory(MongoProperties mongo) {
        ServerAddress serverAddress = new ServerAddress(mongo.getHost(), mongo.getPort());
        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        mongoCredentialList
                .add(MongoCredential.createCredential(mongo.getUsername(), mongo.getAuthenticationDatabase(), mongo.getPassword()));
        return new SimpleMongoDbFactory(new MongoClient(serverAddress, mongoCredentialList), mongo.getDatabase());
    }
}
