package com.nosql.mongo.restapi.service;


import java.util.List;

public interface ProductService<RequestDTO, ResponseDTO> {

    void create(RequestDTO requestDTO);

    void update(String id, RequestDTO requestDTO);

    void delete(String id);

    List<ResponseDTO> getAllItems();

    ResponseDTO getProductById(String id);

    List<ResponseDTO> getProductByName(String name);
}
