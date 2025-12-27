package rnd.dev.inventorymanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "inventories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue
    private UUID inventoryId;

    private UUID productId;

    private int availableQuantity;

    private int reservedQuantity;

    private boolean inStock;

    private String createdAt;

    private String updateAt;
}
