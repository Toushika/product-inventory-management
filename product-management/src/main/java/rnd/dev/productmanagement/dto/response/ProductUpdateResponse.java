package rnd.dev.productmanagement.dto.response;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateResponse {

    private UUID productId;
    private String name;
    private String updatedAt;
    private String message;
}
