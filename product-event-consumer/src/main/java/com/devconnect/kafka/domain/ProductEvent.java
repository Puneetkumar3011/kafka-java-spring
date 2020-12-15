package com.devconnect.kafka.domain;

import lombok.*;

import javax.persistence.*;
import java.awt.print.Book;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class ProductEvent {
    @Id
    @GeneratedValue
    private Integer productEventId;

    @Enumerated(EnumType.STRING)
    private ProductActionType productActionType;

    @OneToOne(mappedBy = "productEvent", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private Product product;
}
