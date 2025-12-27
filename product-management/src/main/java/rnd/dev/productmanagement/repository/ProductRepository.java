package rnd.dev.productmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnd.dev.productmanagement.entity.Product;
import rnd.dev.productmanagement.enums.ProductCategory;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findByProductCategory(ProductCategory productCategory, Pageable pageable);
}
