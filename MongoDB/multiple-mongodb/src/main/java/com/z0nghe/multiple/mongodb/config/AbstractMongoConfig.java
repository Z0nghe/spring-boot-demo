package com.z0nghe.multiple.mongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.Data;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractMongoConfig {
    private String host;
    private String database;
    private String authenticationDatabase;
    private String username;
    private String password;
    private int port;

    /*
     * Method that creates MongoDbFactory Common to both of the MongoDb
     * connections
     */
    public MongoDbFactory mongoDbFactory() throws Exception {
        ServerAddress serverAddress = new ServerAddress(host, port);
        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        mongoCredentialList.add(MongoCredential.createCredential(username, authenticationDatabase, password.toCharArray()));
        return new SimpleMongoDbFactory(new MongoClient(serverAddress, mongoCredentialList), database);
    }

    /*
     * Factory method to create the MongoTemplate
     */
    abstract public MongoTemplate getMongoTemplate() throws Exception;
}
