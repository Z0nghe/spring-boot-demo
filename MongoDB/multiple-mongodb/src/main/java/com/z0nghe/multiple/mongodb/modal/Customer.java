package com.z0nghe.multiple.mongodb.modal;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "test10010")
public class Customer {
    @Id
    public String id;
    public String firstName;
    public String lastName;
    public Score score;
}
