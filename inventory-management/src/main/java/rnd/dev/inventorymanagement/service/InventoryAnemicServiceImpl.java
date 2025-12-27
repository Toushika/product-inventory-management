package rnd.dev.inventorymanagement.service;

import org.springframework.stereotype.Service;
import rnd.dev.inventorymanagement.entity.Inventory;
import rnd.dev.inventorymanagement.repository.InventoryRepository;

import java.util.Optional;

@Service
public class InventoryAnemicServiceImpl implements InventoryAnemicService {

    private final InventoryRepository inventoryRepository;

    public InventoryAnemicServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory reserve(Inventory inventory) {
        return save(inventory);
    }

    @Override
    public Optional<Inventory> findByProductid(String productId) {
        return inventoryRepository.findByProductId(productId);
    }
}
