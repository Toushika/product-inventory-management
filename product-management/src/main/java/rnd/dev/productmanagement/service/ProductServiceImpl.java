package rnd.dev.productmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rnd.dev.productmanagement.dto.request.ProductCreateRequest;
import rnd.dev.productmanagement.dto.request.ProductUpdateRequest;
import rnd.dev.productmanagement.dto.response.ProductCreateResponse;
import rnd.dev.productmanagement.dto.response.ProductDeleteResponse;
import rnd.dev.productmanagement.dto.response.ProductDtoResponse;
import rnd.dev.productmanagement.dto.response.ProductUpdateResponse;
import rnd.dev.productmanagement.entity.Product;
import rnd.dev.productmanagement.enums.ProductCategory;
import rnd.dev.productmanagement.utility.DateTimeConverter;

import java.util.*;

import static rnd.dev.productmanagement.constant.ResponseMessage.*;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductAnemicService productAnemicService;

    public ProductServiceImpl(ProductAnemicService productAnemicService) {
        this.productAnemicService = productAnemicService;
    }

    @Override
    public ProductCreateResponse save(ProductCreateRequest productCreateRequest) {

        log.info("ProductServiceImpl :: save :: createProductRequest : {}", productCreateRequest);

        Product savedProduct = productAnemicService.save(buildProductForCreation(productCreateRequest));
        return buildProductCreateResponse(savedProduct);
    }

    @Override
    public List<ProductDtoResponse> getAll() {
        List<Product> productList = productAnemicService.getAll();
        return buildProductDtoResposList(productList);
    }

    @Override
    public ProductUpdateResponse update(UUID productId, ProductUpdateRequest productUpdateRequest) {

        log.info("ProductServiceImpl :: update :: productId : {} :: productUpdateRequest : {}", productId, productUpdateRequest);

        Product updatedProduct = productAnemicService.update(buildProductForUpdate(productId, productUpdateRequest));
        return buildProductUpdateResponse(updatedProduct);
    }

    @Override
    public ProductDtoResponse getById(UUID productId) {

        log.info("ProductServiceImpl :: getById :: productId : {}", productId);

        Product product = productAnemicService.findById(productId).orElseThrow();
        return buildProductDtoResponse(product);
    }

    @Override
    public Page<ProductDtoResponse> getByCategory(ProductCategory productCategory, int pageNumber, int pageSize) {

        log.info("ProductServiceImpl :: getByCategory :: productCategory : {} :: pageNumber : {} :: pageSize : {}", productCategory, pageNumber, pageSize);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPageResponse = productAnemicService.findByCategory(productCategory, pageable);
        return buildProductDtoResponseForPage(productPageResponse);
    }

    @Override
    public ProductDeleteResponse deleteById(UUID productId) {

        log.info("ProductServiceImpl :: deleteById :: productId : {}", productId);

        if (productAnemicService.findById(productId).isEmpty()) {
            throw new RuntimeException("No Product Found For Deletion");
        }
        boolean productDeletionFlag = productAnemicService.deleteById(productId);
        return prepareProductDeleteResponse(productId, productDeletionFlag);
    }

    @Override
    public boolean isAvailable(UUID productId) {
        return productAnemicService.isAvailable(productId);
    }

    /// /////// Product creation /////////////

    private Product buildProductForCreation(ProductCreateRequest productCreateRequest) {
        return Product.builder()
                .name(productCreateRequest.getName())
                .description(productCreateRequest.getDescription())
                .sku(productCreateRequest.getSku())
                .productCategory(productCreateRequest.getProductCategory())
                .price(productCreateRequest.getPrice())
                .createdAt(DateTimeConverter.getDateTime(new Date()))
                .build();
    }

    private ProductCreateResponse buildProductCreateResponse(Product product) {
        return ProductCreateResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .createdAt(product.getCreatedAt())
                .message(PRODUCT_RECORDED_MESSAGE)
                .build();
    }

    ////////// End of product creation /////////////

    /// /////// For Product Viewing all /////////////

    private List<ProductDtoResponse> buildProductDtoResposList(List<Product> productList) {

        List<ProductDtoResponse> productDtoResponseList = new ArrayList<>();

        for (Product product : productList) {
            productDtoResponseList.add(buildProductDtoResponse(product));
        }
        return productDtoResponseList;

    }

    private ProductDtoResponse buildProductDtoResponse(Product product) {
        return ProductDtoResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .sku(product.getSku())
                .productCategory(product.getProductCategory())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    ////////// End of Product Viewing all /////////////

    /// /////// Product Update /////////////

    private Product buildProductForUpdate(UUID productId, ProductUpdateRequest productUpdateRequest) {
        Optional<Product> productOptional = productAnemicService.findById(productId);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Need Valid Product Id for Update");
        }

        Product product = productOptional.get();

        if (productUpdateRequest.getName() != null) {
            product.setName(productUpdateRequest.getName());
        }

        if (productUpdateRequest.getDescription() != null) {
            product.setDescription(productUpdateRequest.getDescription());
        }

        if (productUpdateRequest.getProductCategory() != null) {
            product.setProductCategory(productUpdateRequest.getProductCategory());
        }

        if (productUpdateRequest.getPrice() != null) {
            product.setPrice(productUpdateRequest.getPrice());
        }

        product.setUpdatedAt(DateTimeConverter.getDateTime(new Date()));

        return product;
    }


    private ProductUpdateResponse buildProductUpdateResponse(Product product) {
        return ProductUpdateResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .updatedAt(product.getUpdatedAt())
                .message(PRODUCT_UPDATE_MESSAGE)
                .build();
    }

    /// /////// End of Product Update /////////////

    private Page<ProductDtoResponse> buildProductDtoResponseForPage(Page<Product> productPage) {
        return productPage.map(this::buildProductDtoResponse);
    }

    private ProductDeleteResponse prepareProductDeleteResponse(UUID productId, boolean productDeletionFlag) {
        if (productDeletionFlag) {
            return buildProductDeletionResponse(productId, SUCCESSFUL_DELETION_MESSAGE);

        } else {
            return buildProductDeletionResponse(productId, UNSUCCESSFUL_DELETION_MESSAGE);
        }
    }

    private static ProductDeleteResponse buildProductDeletionResponse(UUID productId, String message) {
        return ProductDeleteResponse.builder()
                .productId(productId)
                .message(message)
                .build();
    }

}
