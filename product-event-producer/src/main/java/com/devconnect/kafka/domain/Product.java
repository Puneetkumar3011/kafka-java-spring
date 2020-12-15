package com.devconnect.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private Integer productId;

    @NotBlank
    private String productName;

    @NotBlank
    private String productDescription;
}
