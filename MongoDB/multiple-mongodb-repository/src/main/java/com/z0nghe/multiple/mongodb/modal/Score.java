package com.z0nghe.multiple.mongodb.modal;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "test1")
public class Score {

    @Id
    public String id;
    public String className;

    public double score;

}
