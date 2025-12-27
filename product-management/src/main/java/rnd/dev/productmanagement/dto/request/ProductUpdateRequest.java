package rnd.dev.productmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rnd.dev.productmanagement.enums.ProductCategory;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {

    private String name;
    private String description;
    private ProductCategory productCategory;
    private Double price;
}
