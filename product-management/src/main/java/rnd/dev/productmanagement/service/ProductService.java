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

public interface ProductService {

    ProductCreateResponse save(ProductCreateRequest productCreateRequest);

    List<ProductDtoResponse> getAll();

    ProductDtoResponse getById(String productId);

    Page<ProductDtoResponse> getByCategory(ProductCategory productCategory, int page, int size);

    ProductUpdateResponse update(String productId, ProductUpdateRequest productUpdateRequest);

    ProductDeleteResponse deleteById(String productId);

    boolean isAvailable(String productId);

}
