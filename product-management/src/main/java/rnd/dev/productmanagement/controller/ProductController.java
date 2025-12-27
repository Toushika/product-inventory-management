package rnd.dev.productmanagement.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import rnd.dev.productmanagement.dto.request.ProductCreateRequest;
import rnd.dev.productmanagement.dto.request.ProductUpdateRequest;
import rnd.dev.productmanagement.dto.response.ProductCreateResponse;
import rnd.dev.productmanagement.dto.response.ProductDeleteResponse;
import rnd.dev.productmanagement.dto.response.ProductDtoResponse;
import rnd.dev.productmanagement.dto.response.ProductUpdateResponse;
import rnd.dev.productmanagement.enums.ProductCategory;
import rnd.dev.productmanagement.service.ProductService;

import java.util.List;

import static rnd.dev.productmanagement.constant.ApiUrlConstants.*;
import static rnd.dev.productmanagement.constant.ParameterConstants.*;
import static rnd.dev.productmanagement.constant.ResponseMessage.HELLO_PRODUCT_MANAGEMENT_MESSAGE;

@RestController
public class ProductController extends AbstractController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(INDEX_URL)
    public String home() {
        return HELLO_PRODUCT_MANAGEMENT_MESSAGE;
    }

    @PostMapping(SAVE_URL)
    public ProductCreateResponse save(@RequestBody ProductCreateRequest productCreateRequest) {
        return productService.save(productCreateRequest);
    }

    @GetMapping(GET_ALL_URL)
    public List<ProductDtoResponse> getAll() {
        return productService.getAll();
    }

    @PutMapping(UPDATE_BY_ID_URL)
    public ProductUpdateResponse update(@PathVariable(name = PRODUCT_ID) String productId, @RequestBody ProductUpdateRequest productUpdateRequest) {
        return productService.update(productId, productUpdateRequest);
    }

    @GetMapping(GET_BY_ID)
    public ProductDtoResponse getById(@RequestParam(name = PRODUCT_ID) String productId) {
        return productService.getById(productId);
    }

    @GetMapping(GET_BY_CATEGORY)
    public Page<ProductDtoResponse> getByCategory(@RequestParam(name = PRODUCT_CATEGORY) ProductCategory productCategory,
                                                  @RequestParam(defaultValue = PAGE_NUMBER) int page,
                                                  @RequestParam(defaultValue = PAGE_SIZE) int size) {
        return productService.getByCategory(productCategory, page, size);
    }

    @DeleteMapping(DELETE_BY_ID_URL)
    public ProductDeleteResponse deleteById(@PathVariable(name = PRODUCT_ID) String productId) {
        return productService.deleteById(productId);
    }

    @GetMapping(AVAILABILITY_CHECK_URL)
    public boolean isAvailable(@PathVariable(name = PRODUCT_ID) String productId) {
        return productService.isAvailable(productId);
    }

}
