package com.z0nghe.mongodb.repository;

import com.z0nghe.mongodb.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {

    Student findFirstByName(String name);

    List<Student> findAllByName(String name);

}
