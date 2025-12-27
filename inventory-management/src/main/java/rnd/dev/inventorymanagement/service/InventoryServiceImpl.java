package rnd.dev.inventorymanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rnd.dev.inventorymanagement.dto.request.InventoryCreateRequest;
import rnd.dev.inventorymanagement.dto.request.InventoryUpdateRequest;
import rnd.dev.inventorymanagement.dto.response.InventoryCreateResponse;
import rnd.dev.inventorymanagement.dto.response.InventoryUpdateResponse;
import rnd.dev.inventorymanagement.entity.Inventory;
import rnd.dev.inventorymanagement.error.exception.ProductInsufficientQuantityException;
import rnd.dev.inventorymanagement.error.exception.ProductNotFoundException;
import rnd.dev.inventorymanagement.helper.ProductClient;
import rnd.dev.inventorymanagement.utility.DateTimeUtility;
import rnd.dev.inventorymanagement.utility.IdGeneratorUtility;

import static rnd.dev.inventorymanagement.constant.ExceptionMessageConstants.PRODUCT_AVAILABILITY_INSUFFICIENT;
import static rnd.dev.inventorymanagement.constant.ExceptionMessageConstants.PRODUCT_DOES_NOT_EXIST_MESSAGE;
import static rnd.dev.inventorymanagement.constant.ResponseMessageConstants.INVENTORY_SUCCESSFUL_CREATION_MESSAGE;
import static rnd.dev.inventorymanagement.constant.ResponseMessageConstants.INVENTORY_SUCCESSFUL_UPDATE_MESSAGE;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryAnemicService inventoryAnemicService;
    private final ProductClient productClient;

    public InventoryServiceImpl(InventoryAnemicService inventoryAnemicService, ProductClient productClient) {
        this.inventoryAnemicService = inventoryAnemicService;
        this.productClient = productClient;
    }

    @Override
    @Transactional
    public InventoryCreateResponse save(InventoryCreateRequest inventoryCreateRequest) {

        log.info("InventoryServiceImpl :: save :: inventoryCreateRequest : {}", inventoryCreateRequest);

        boolean isProductExists = productClient.productExists(inventoryCreateRequest.getProductId());
        if (!isProductExists) {
            throw new ProductNotFoundException(PRODUCT_DOES_NOT_EXIST_MESSAGE);
        }
        Inventory inventory = inventoryAnemicService.save(buildInventory(inventoryCreateRequest));
        return buildInventoryCreateResponse(inventory);
    }

    @Override
    @Transactional
    public InventoryUpdateResponse reserve(InventoryUpdateRequest inventoryUpdateRequest) {
        Inventory inventory = inventoryAnemicService.findByProductid(inventoryUpdateRequest.getProductId()).orElseThrow(() -> new RuntimeException("No inventory Found"));

        if (inventory.getAvailableQuantity() < inventoryUpdateRequest.getReservedQuantity()) {
            throw new ProductInsufficientQuantityException(PRODUCT_AVAILABILITY_INSUFFICIENT);
        }

        inventory.setAvailableQuantity(inventory.getAvailableQuantity() - inventoryUpdateRequest.getReservedQuantity());
        inventory.setInStock(inventory.getAvailableQuantity() > 0);
        inventory.setReservedQuantity(inventoryUpdateRequest.getReservedQuantity());
        inventory.setUpdateAt(DateTimeUtility.getCurrentDateTime());

        inventoryAnemicService.save(inventory);

        return buildInventoryUpdateResponse(inventory);
    }

    private Inventory buildInventory(InventoryCreateRequest inventoryCreateRequest) {
        return Inventory.builder()
                .inventoryId(IdGeneratorUtility.generateId())
                .productId(inventoryCreateRequest.getProductId())
                .availableQuantity(inventoryCreateRequest.getAvailableQuantity())
                .inStock(true)
                .createdAt(DateTimeUtility.getCurrentDateTime())
                .build();
    }

    private InventoryCreateResponse buildInventoryCreateResponse(Inventory inventory) {

        return InventoryCreateResponse.builder()
                .inventoryId(inventory.getInventoryId())
                .productId(inventory.getProductId())
                .availableQuantity(inventory.getAvailableQuantity())
                .inStock(inventory.isInStock())
                .createdAt(inventory.getCreatedAt())
                .message(INVENTORY_SUCCESSFUL_CREATION_MESSAGE)
                .build();
    }

    private InventoryUpdateResponse buildInventoryUpdateResponse(Inventory inventory) {
        return InventoryUpdateResponse.builder()
                .inventoryId(inventory.getInventoryId())
                .productId(inventory.getProductId())
                .availableQuantity(inventory.getAvailableQuantity())
                .inStock(inventory.isInStock())
                .updateAt(inventory.getUpdateAt())
                .message(INVENTORY_SUCCESSFUL_UPDATE_MESSAGE)
                .build();
    }
}
