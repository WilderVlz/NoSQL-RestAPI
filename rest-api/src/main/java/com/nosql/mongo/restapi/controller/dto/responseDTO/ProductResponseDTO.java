package com.nosql.mongo.restapi.controller.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
}
