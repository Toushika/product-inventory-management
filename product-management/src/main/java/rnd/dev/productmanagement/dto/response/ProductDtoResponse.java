package rnd.dev.productmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rnd.dev.productmanagement.enums.ProductCategory;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoResponse {

    private String productId;
    private String name;
    private String description;
    private String sku;
    private ProductCategory productCategory;
    private Double price;
    private String createdAt;
    private String updatedAt;
}
