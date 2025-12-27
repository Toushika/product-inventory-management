package rnd.dev.productmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateResponse {

    private String productId;
    private String name;
    private String createdAt;
    private String message;

}