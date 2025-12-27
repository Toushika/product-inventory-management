package rnd.dev.productmanagement.service;

import org.springframework.data.domain.Page;
import rnd.dev.productmanagement.dto.request.ProductCreateRequest;
import rnd.dev.productmanagement.dto.request.ProductUpdateRequest;
import rnd.dev.productmanagement.dto.response.ProductCreateResponse;
import rnd.dev.productmanagement.dto.response.ProductDeleteResponse;
import rnd.dev.productmanagement.dto.response.ProductDtoResponse;
import rnd.dev.productmanagement.dto.response.ProductUpdateResponse;
import rnd.dev.productmanagement.enums.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductCreateResponse save(ProductCreateRequest productCreateRequest);

    List<ProductDtoResponse> getAll();

    ProductDtoResponse getById(UUID productId);

    Page<ProductDtoResponse> getByCategory(ProductCategory productCategory, int page, int size);

    ProductUpdateResponse update(UUID productId, ProductUpdateRequest productUpdateRequest);

    ProductDeleteResponse deleteById(UUID productId);

    boolean isAvailable(UUID productId);

}
