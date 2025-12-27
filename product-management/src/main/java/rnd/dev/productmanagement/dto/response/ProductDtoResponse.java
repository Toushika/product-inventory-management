package rnd.dev.productmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rnd.dev.productmanagement.enums.ProductCategory;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoResponse {

    private UUID productId;
    private String name;
    private String description;
    private String sku;
    private ProductCategory productCategory;
    private Double price;
    private String createdAt;
    private String updatedAt;
}
