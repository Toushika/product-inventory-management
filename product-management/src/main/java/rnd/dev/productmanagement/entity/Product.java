package rnd.dev.productmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rnd.dev.productmanagement.enums.ProductCategory;

import static rnd.dev.productmanagement.constant.TableConstants.PRODUCTS_TABLE;

@Entity
@Table(name = PRODUCTS_TABLE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String productId;

    private String name;

    private String description;

    @Column(nullable = false, unique = true)
    private String sku;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    private Double price;

    private String createdAt;

    private String updatedAt;
}
