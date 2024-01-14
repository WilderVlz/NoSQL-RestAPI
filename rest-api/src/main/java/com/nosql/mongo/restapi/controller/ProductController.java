package com.nosql.mongo.restapi.controller;

import com.nosql.mongo.restapi.controller.dto.requestDTO.ProductRequestDTO;
import com.nosql.mongo.restapi.controller.dto.responseDTO.ProductResponseDTO;
import com.nosql.mongo.restapi.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @Operation(summary = "Create a new product", description = "returns a message if the product was successfully created and 201 code, status 400 if the product was not created")
    @PostMapping(value = "/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequestDTO product) {
        try {
            this.productService.create(product);
            return new ResponseEntity<>("Product was successfully created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create product " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update an existing product", description = "returns a message if the product was successfully updated and 200 code, status 400 if the product was not updated")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @Valid @RequestBody ProductRequestDTO product) {
        try {
            this.productService.update(id, product);
            return ResponseEntity.ok("Product was successfully updated");
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update product " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete an existing product", description = "returns a message if the product was successfully deleted and 200 code, status 400 if the product was not deleted")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        try {
            this.productService.delete(id);
            return ResponseEntity.ok("Product was successfully deleted");
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete product " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Get all existing products", description = "returns a list of all products and 200 code, status 404 if the products were not found")
    // produces = "application/json" means that the method will only return JSON response
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        try {
            return ResponseEntity.ok(this.productService.getAllItems());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get an existing product by id", description = "returns a product and 200 code, status 404 if the product was not found")
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(this.productService.getProductById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get an existing product by name", description = "returns a list of products and 200 code, status 404 if the products were not found")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByName(@PathVariable String name) {
        try {
            return new ResponseEntity<>(this.productService.getProductByName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
