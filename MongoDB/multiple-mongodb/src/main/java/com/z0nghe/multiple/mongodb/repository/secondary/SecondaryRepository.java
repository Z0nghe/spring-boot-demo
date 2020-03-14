package com.z0nghe.multiple.mongodb.repository.secondary;


import com.z0nghe.multiple.mongodb.modal.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondaryRepository extends MongoRepository<Score, String> {

}
