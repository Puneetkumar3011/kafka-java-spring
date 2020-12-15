package com.devconnect.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ProductEventId;

    @Enumerated(EnumType.STRING)
    private ProductActionType productActionType;

    @NotNull
    @Valid
    private Product product;
}
