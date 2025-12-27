package rnd.dev.productmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rnd.dev.productmanagement.dto.request.ProductCreateRequest;
import rnd.dev.productmanagement.dto.request.ProductUpdateRequest;
import rnd.dev.productmanagement.dto.response.ProductCreateResponse;
import rnd.dev.productmanagement.dto.response.ProductDeleteResponse;
import rnd.dev.productmanagement.dto.response.ProductDtoResponse;
import rnd.dev.productmanagement.dto.response.ProductUpdateResponse;
import rnd.dev.productmanagement.entity.Product;
import rnd.dev.productmanagement.enums.ProductCategory;
import rnd.dev.productmanagement.error.exception.ProductNotFoundException;
import rnd.dev.productmanagement.utility.DateTimeUtility;
import rnd.dev.productmanagement.utility.IdGeneratorUtility;

import java.util.*;

import static rnd.dev.productmanagement.constant.ResponseMessage.*;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductAnemicService productAnemicService;

    public ProductServiceImpl(ProductAnemicService productAnemicService) {
        this.productAnemicService = productAnemicService;
    }

    @Transactional
    @Override
    public ProductCreateResponse save(ProductCreateRequest productCreateRequest) {

        log.info("ProductServiceImpl :: save :: createProductRequest : {}", productCreateRequest);

        Product savedProduct = productAnemicService.save(buildProductForCreation(productCreateRequest));
        return buildProductCreateResponse(savedProduct);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDtoResponse> getAll() {
        List<Product> productList = productAnemicService.getAll();
        return buildProductDtoResposList(productList);
    }

    @Transactional
    @Override
    public ProductUpdateResponse update(String productId, ProductUpdateRequest productUpdateRequest) {

        log.info("ProductServiceImpl :: update :: productId : {} :: productUpdateRequest : {}", productId, productUpdateRequest);

        Product updatedProduct = productAnemicService.update(buildProductForUpdate(productId, productUpdateRequest));
        return buildProductUpdateResponse(updatedProduct);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDtoResponse getById(String productId) {

        log.info("ProductServiceImpl :: getById :: productId : {}", productId);

        Product product = productAnemicService.findById(productId).orElseThrow();
        return buildProductDtoResponse(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductDtoResponse> getByCategory(ProductCategory productCategory, int pageNumber, int pageSize) {

        log.info("ProductServiceImpl :: getByCategory :: productCategory : {} :: pageNumber : {} :: pageSize : {}", productCategory, pageNumber, pageSize);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPageResponse = productAnemicService.findByCategory(productCategory, pageable);
        return buildProductDtoResponseForPage(productPageResponse);
    }

    @Transactional
    @Override
    public ProductDeleteResponse deleteById(String productId) {

        log.info("ProductServiceImpl :: deleteById :: productId : {}", productId);

        if (productAnemicService.findById(productId).isEmpty()) {
            throw new ProductNotFoundException("No Product Found For Deletion");
        }
        boolean productDeletionFlag = productAnemicService.deleteById(productId);
        return prepareProductDeleteResponse(productId, productDeletionFlag);
    }
    @Transactional(readOnly = true)
    @Override
    public boolean isAvailable(String productId) {
        return productAnemicService.isAvailable(productId);
    }

    /// /////// Product creation /////////////

    private Product buildProductForCreation(ProductCreateRequest productCreateRequest) {
        return Product.builder()
                .productId(IdGeneratorUtility.generateId())
                .name(productCreateRequest.getName())
                .description(productCreateRequest.getDescription())
                .sku(productCreateRequest.getSku())
                .productCategory(productCreateRequest.getProductCategory())
                .price(productCreateRequest.getPrice())
                .createdAt(DateTimeUtility.getDateTime(new Date()))
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

    private Product buildProductForUpdate(String productId, ProductUpdateRequest productUpdateRequest) {
        Optional<Product> productOptional = productAnemicService.findById(productId);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Need Valid Product Id for Update");
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

        product.setUpdatedAt(DateTimeUtility.getDateTime(new Date()));

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

    private ProductDeleteResponse prepareProductDeleteResponse(String productId, boolean productDeletionFlag) {
        if (productDeletionFlag) {
            return buildProductDeletionResponse(productId, SUCCESSFUL_DELETION_MESSAGE);

        } else {
            return buildProductDeletionResponse(productId, UNSUCCESSFUL_DELETION_MESSAGE);
        }
    }

    private static ProductDeleteResponse buildProductDeletionResponse(String productId, String message) {
        return ProductDeleteResponse.builder()
                .productId(productId)
                .message(message)
                .build();
    }

}
