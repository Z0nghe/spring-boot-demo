package com.z0nghe.mongodb.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "Student1")
public class Student {
    @Id
    private String id;
    private String name;
    private int age;
}
