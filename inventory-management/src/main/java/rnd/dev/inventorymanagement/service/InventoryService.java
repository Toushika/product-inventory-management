package rnd.dev.inventorymanagement.service;

import rnd.dev.inventorymanagement.dto.request.InventoryCreateRequest;
import rnd.dev.inventorymanagement.dto.request.InventoryUpdateRequest;
import rnd.dev.inventorymanagement.dto.response.InventoryCreateResponse;
import rnd.dev.inventorymanagement.dto.response.InventoryUpdateResponse;

public interface InventoryService {

    InventoryCreateResponse save(InventoryCreateRequest inventoryCreateRequest);

    InventoryUpdateResponse reserve(InventoryUpdateRequest inventoryUpdateRequest);

}
