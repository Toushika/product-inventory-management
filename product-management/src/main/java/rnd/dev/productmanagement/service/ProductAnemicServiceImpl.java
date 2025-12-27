package rnd.dev.productmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rnd.dev.productmanagement.entity.Product;
import rnd.dev.productmanagement.enums.ProductCategory;
import rnd.dev.productmanagement.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductAnemicServiceImpl implements ProductAnemicService {

    private final ProductRepository productRepository;

    public ProductAnemicServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        return save(product);
    }

    @Override
    public Optional<Product> findById(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Page<Product> findByCategory(ProductCategory productCategory, Pageable pageable) {
        return productRepository.findByProductCategory(productCategory, pageable);
    }

    @Override
    public boolean deleteById(String productId) {
        productRepository.deleteById(productId);
        return findById(productId).isEmpty();
    }

    @Override
    public boolean isAvailable(String productId) {
        return productRepository.existsById(productId);
    }
}
