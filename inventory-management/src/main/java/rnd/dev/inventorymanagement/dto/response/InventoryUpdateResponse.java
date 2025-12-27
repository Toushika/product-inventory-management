package rnd.dev.inventorymanagement.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryUpdateResponse extends InventoryDtoResponse {

    private String updateAt;
    private String message;
}
