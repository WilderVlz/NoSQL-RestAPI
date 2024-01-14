package com.nosql.mongo.restapi.service.impl;

import com.nosql.mongo.restapi.controller.dto.requestDTO.ProductRequestDTO;
import com.nosql.mongo.restapi.controller.dto.responseDTO.ProductResponseDTO;
import com.nosql.mongo.restapi.model.Product;
import com.nosql.mongo.restapi.repository.ProductRepository;
import com.nosql.mongo.restapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService<ProductRequestDTO, ProductResponseDTO> {
    @Autowired
    private ProductRepository productRepository;

    /**
     * @param product
     */
    @Override
    public void create(ProductRequestDTO product) {
        this.productRepository.save(Product.builder()
                .imagePath(product.getImagePath())
                .name(product.getName())
                .description(product.getDescription())
                .build());
    }

    /**
     * @param id
     * @param product
     * @throws RuntimeException if the product was not found
     */
    @Override
    public void update(String id, ProductRequestDTO product) {
        Product productToUpdate = findProductById(id);
        productToUpdate.setId(id);
        productToUpdate.setImagePath(product.getImagePath());
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        this.productRepository.save(productToUpdate);
    }

    /**
     * @param id
     * @throws RuntimeException if the product was not found
     */
    @Override
    public void delete(String id) {
        findProductById(id);
        this.productRepository.deleteById(id);
    }

    /**
     * @return List<Product>
     */
    @Override
    public List<ProductResponseDTO> getAllItems() {
        List<ProductResponseDTO> productList = new ArrayList<>();
        for (Product product : this.productRepository.findAll()) {
            productList.add(ProductResponseDTO.builder()
                    .name(product.getName())
                    .description(product.getDescription())
                    .build());
        }
        return productList;
    }

    /**
     * @param id
     * @return Product
     * @throws RuntimeException if the product was not found
     */
    @Override
    public ProductResponseDTO getProductById(String id) {
        Product product = findProductById(id);
        return ProductResponseDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }

    /**
     * @param id
     * @return Product
     * @throws RuntimeException if the product was not found
     */
    private Product findProductById(String id) {
        return this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    /**
     * @param name
     * @return List<Product>
     * @throws RuntimeException if the products were not found
     */
    @Override
    public List<ProductResponseDTO> getProductByName(String name) {
        List<Product> products = this.productRepository.findByName(name);
        if (products.isEmpty()) {
            throw new RuntimeException("Products with title " + name + " not found");
        } else {
            List<ProductResponseDTO> productList = new ArrayList<>();
            for (Product product : products) {
                productList.add(ProductResponseDTO.builder()
                        .name(product.getName())
                        .description(product.getDescription())
                        .build());
            }
            return productList;
        }
    }
}
