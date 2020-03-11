package com.z0nghe.multiple.mongodb;

import com.z0nghe.multiple.mongodb.modal.Customer;
import com.z0nghe.multiple.mongodb.modal.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class MultipleMongodbApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MultipleMongodbApplication.class, args);
    }

    @Autowired
    @Qualifier(value = "primaryMongoTemplate")
    protected MongoTemplate primaryMongoTemplate;

    // Using mongoTemplate for secondary database
    @Autowired
    @Qualifier(value = "secondaryMongoTemplate")
    protected MongoTemplate secondaryMongoTemplate;


    @Override
    public void run(String... args) {

        Customer zonghe = Customer.builder().firstName("Zong").lastName("He").build();
        Score myScore = Score.builder().className("math").score(98.4).build();
        zonghe.setScore(myScore);

        primaryMongoTemplate.save(zonghe);

        secondaryMongoTemplate.save(myScore);

    }
}
