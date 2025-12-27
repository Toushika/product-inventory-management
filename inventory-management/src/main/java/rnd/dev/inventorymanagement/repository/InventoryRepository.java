package rnd.dev.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnd.dev.inventorymanagement.entity.Inventory;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {
    Optional<Inventory> findByProductId(String productId);
}
