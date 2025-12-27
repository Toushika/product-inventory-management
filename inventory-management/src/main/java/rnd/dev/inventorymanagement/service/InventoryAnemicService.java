package rnd.dev.inventorymanagement.service;

import rnd.dev.inventorymanagement.entity.Inventory;

import java.util.Optional;
import java.util.UUID;

public interface InventoryAnemicService {

    Inventory save(Inventory inventory);

    Inventory reserve(Inventory inventory);

    Optional<Inventory> findByProductid(UUID productId);
}
