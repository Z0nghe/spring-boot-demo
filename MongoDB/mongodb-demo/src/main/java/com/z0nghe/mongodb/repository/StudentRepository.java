package com.z0nghe.mongodb.repository;

import com.z0nghe.mongodb.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Student findFirstByName(String name);

    List<Student> findAllByName(String name);
}
