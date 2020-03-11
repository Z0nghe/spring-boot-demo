package com.z0nghe.multiple.mongodb;

import com.z0nghe.multiple.mongodb.config.props.MultipleMongoProperties;
import com.z0nghe.multiple.mongodb.modal.Customer;
import com.z0nghe.multiple.mongodb.modal.Score;
import com.z0nghe.multiple.mongodb.repository.primary.PrimaryRepository;
import com.z0nghe.multiple.mongodb.repository.secondary.SecondaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/*
由于配置repository需要用到自己定义的properties
所以在启动时禁用了MongoAutoConfiguration自动配置
并使用MultipleMongoProperties作为默认配置文件配置
 */
@Slf4j
@EnableConfigurationProperties(MultipleMongoProperties.class)
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class MultipleMongodbRepositoryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MultipleMongodbRepositoryApplication.class, args);
    }

    @Autowired
    private PrimaryRepository primaryRepository;

    @Autowired
    private SecondaryRepository secondaryRepository;

    @Override
    public void run(String... args) {

        Customer zonghe = Customer.builder().firstName("Zong").lastName("He").build();
        Score myScore = Score.builder().className("math").score(98.4).build();
        zonghe.setScore(myScore);

        primaryRepository.deleteAll();
        primaryRepository.save(zonghe);

        Customer customer = primaryRepository.findFirstByScore(myScore);
        log.info("*******");
        log.info(customer.toString());
        log.info("*******");

        secondaryRepository.deleteAll();
        secondaryRepository.save(zonghe);

    }
}
