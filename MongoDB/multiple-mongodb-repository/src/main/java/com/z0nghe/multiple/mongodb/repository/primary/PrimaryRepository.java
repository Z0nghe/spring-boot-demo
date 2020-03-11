package com.z0nghe.multiple.mongodb.repository.primary;

import com.z0nghe.multiple.mongodb.modal.Customer;
import com.z0nghe.multiple.mongodb.modal.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimaryRepository extends MongoRepository<Customer, String> {

    Customer findFirstByScore(Score score);
}
