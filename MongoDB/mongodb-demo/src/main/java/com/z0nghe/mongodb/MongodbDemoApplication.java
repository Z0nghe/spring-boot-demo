package com.z0nghe.mongodb;

import com.z0nghe.mongodb.model.Student;
import com.z0nghe.mongodb.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
@Slf4j
public class MongodbDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MongodbDemoApplication.class, args);
    }

    @Autowired
    private StudentRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        Student student = Student.builder().name("z0nghe").age(25).build();
        repository.save(student);
        Student student2 = Student.builder().name("puck").age(10000).build();
        mongoTemplate.save(student2);

        log.info("{}", repository.findFirstByName("puck"));
        log.info("{}", mongoTemplate.findOne(Query.query(Criteria.where("name").is("z0nghe")), Student.class));
    }
}
