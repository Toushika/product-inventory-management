package rnd.dev.inventorymanagement.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCreateResponse extends InventoryDtoResponse {

    private String createdAt;
    private String message;
}
