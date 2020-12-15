package com.devconnect.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer productId;

    @NotBlank
    private String productName;

    @NotBlank
    private String productDescription;

    @OneToOne
    @JoinColumn(name = "ProductEventId")
    private ProductEvent productEvent;
}
