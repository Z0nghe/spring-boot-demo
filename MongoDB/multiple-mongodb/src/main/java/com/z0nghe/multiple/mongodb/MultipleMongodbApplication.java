package com.z0nghe.multiple.mongodb;

import com.z0nghe.multiple.mongodb.modal.Customer;
import com.z0nghe.multiple.mongodb.modal.Score;
import com.z0nghe.multiple.mongodb.repository.primary.PrimaryRepository;
import com.z0nghe.multiple.mongodb.repository.secondary.SecondaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
@Slf4j
public class MultipleMongodbApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MultipleMongodbApplication.class, args);
    }

    @Autowired
    @Qualifier(value = "primaryMongoTemplate")
    private MongoTemplate primaryMongoTemplate;

    @Autowired
    @Qualifier(value = "secondaryMongoTemplate")
    private MongoTemplate secondaryMongoTemplate;

    @Autowired
    private PrimaryRepository primaryRepository;

    @Autowired
    private SecondaryRepository secondaryRepository;


    @Override
    public void run(String... args) {
        primaryRepository.deleteAll();
        secondaryRepository.deleteAll();

        Customer zonghe = Customer.builder().firstName("Zong").lastName("He").build();
        Score myScore = Score.builder().className("math").score(98.4).build();
        zonghe.setScore(myScore);

        primaryMongoTemplate.save(zonghe);

        secondaryMongoTemplate.save(myScore);

        primaryRepository.findAll().forEach(customer -> log.info("{}", customer));
        secondaryRepository.findAll().forEach(customer -> log.info("{}", customer));

    }
}
