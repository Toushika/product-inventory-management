package rnd.dev.inventorymanagement.service;

import rnd.dev.inventorymanagement.entity.Inventory;

import java.util.Optional;

public interface InventoryAnemicService {

    Inventory save(Inventory inventory);

    Inventory reserve(Inventory inventory);

    Optional<Inventory> findByProductid(String productId);
}
