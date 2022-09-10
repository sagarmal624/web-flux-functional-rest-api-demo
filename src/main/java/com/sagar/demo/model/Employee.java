package com.sagar.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee")
@Data
public class Employee {
    @Id
    private String id;

    private String name;
    private String email;
    private String department;
    private Integer salary;
}
