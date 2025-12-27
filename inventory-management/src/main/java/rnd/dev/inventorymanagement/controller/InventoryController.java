package rnd.dev.inventorymanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rnd.dev.inventorymanagement.dto.request.InventoryCreateRequest;
import rnd.dev.inventorymanagement.dto.request.InventoryUpdateRequest;
import rnd.dev.inventorymanagement.dto.response.InventoryCreateResponse;
import rnd.dev.inventorymanagement.dto.response.InventoryUpdateResponse;
import rnd.dev.inventorymanagement.service.InventoryService;

import static rnd.dev.inventorymanagement.constant.ApiUrlConstants.RESERVE_URL;
import static rnd.dev.inventorymanagement.constant.ApiUrlConstants.SAVE_URL;

@RestController
public class InventoryController extends AbstractController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping(SAVE_URL)
    public InventoryCreateResponse save(@RequestBody InventoryCreateRequest inventoryCreateRequest) {
        return inventoryService.save(inventoryCreateRequest);
    }

    @PostMapping(RESERVE_URL)
    public InventoryUpdateResponse reserve(@RequestBody InventoryUpdateRequest inventoryUpdateRequest) {
        return inventoryService.reserve(inventoryUpdateRequest);
    }

}
