package rnd.dev.productmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rnd.dev.productmanagement.entity.Product;
import rnd.dev.productmanagement.enums.ProductCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductAnemicService {

    Product save(Product product);

    List<Product> getAll();

    Product update(Product product);

    Optional<Product> findById(UUID productId);

    Page<Product> findByCategory(ProductCategory productCategory, Pageable pageable);

    boolean deleteById(UUID productId);

    boolean isAvailable(UUID productId);
}
