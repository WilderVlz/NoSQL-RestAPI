package com.nosql.mongo.restapi.controller.dto.requestDTO;

import jakarta.validation.constraints.NotBlank;
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
public class ProductRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank(message = "Image path can't be blank")
    private String imagePath;
    @NotBlank(message = "Title can't be blank")
    private String name;
    private String description;
}
