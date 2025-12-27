package rnd.dev.inventorymanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDtoResponse {

    private UUID inventoryId;

    private UUID productId;

    private int availableQuantity;

    private int reservedQuantity;

    private boolean inStock;

}
