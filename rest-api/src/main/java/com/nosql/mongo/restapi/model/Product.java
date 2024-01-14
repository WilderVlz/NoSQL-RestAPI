package com.nosql.mongo.restapi.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// @Document(collection = "product") is used to specify the name of the collection where the database will be stored
@Document(collection = "products")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    // ObjectId is used to generate a unique identifier for each document in the collection
    private String id;
    private String imagePath;
    private String name;
    private String description;

}
