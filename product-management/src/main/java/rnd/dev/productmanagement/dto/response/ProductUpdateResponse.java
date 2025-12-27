package rnd.dev.productmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateResponse {

    private String productId;
    private String name;
    private String updatedAt;
    private String message;
}
