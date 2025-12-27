package rnd.dev.inventorymanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static rnd.dev.inventorymanagement.constant.TableConstants.INVENTORIES_TABLE;

@Entity
@Table(name = INVENTORIES_TABLE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    private String inventoryId;

    private String productId;

    private int availableQuantity;

    private int reservedQuantity;

    private boolean inStock;

    private String createdAt;

    private String updateAt;
}
