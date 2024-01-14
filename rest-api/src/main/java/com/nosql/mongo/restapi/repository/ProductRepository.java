package com.nosql.mongo.restapi.repository;

import com.nosql.mongo.restapi.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    /**
     * @param name
     * @return List<Product>
     */
    List<Product> findByName(String name);
}
