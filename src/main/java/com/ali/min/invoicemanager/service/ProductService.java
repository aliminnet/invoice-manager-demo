package com.ali.min.invoicemanager.service;

import com.ali.min.invoicemanager.dto.ProductDTO;
import com.ali.min.invoicemanager.entity.ProductEntity;
import com.ali.min.invoicemanager.mapper.ProductMapper;
import com.ali.min.invoicemanager.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Product
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    /**
     * Get all products
     *
     * @return list of products
     */
    @Cacheable("products")
    public List<ProductDTO> getProducts() {
        log.info("Getting all products");
        return productRepository.findAll().stream().map(productMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Get product by id
     *
     * @param id product id
     * @return product
     */
    @Cacheable(value = "products", key = "#p0")
    public ProductDTO getProduct(Long id) {
        log.info("Getting product by id: {}", id);
        return productRepository.findById(id).map(productMapper::toDTO).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }


    /**
     * Save product
     *
     * @param productDTO product to be saved
     * @return saved product
     */
    @CacheEvict(value = "products", allEntries = true)
    public ProductDTO saveProduct(ProductDTO productDTO) {
        log.info("Saving product: {}", productDTO);
        ProductEntity productEntity = productMapper.toEntity(productDTO);
        ProductEntity saved = productRepository.save(productEntity);
        return productMapper.toDTO(saved);
    }

    /**
     * Update product
     *
     * @param id product id
     * @param productDTO product to be updated
     * @return updated product
     */
    @CachePut(value = "products", key = "#p0")
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        log.info("Updating product with id: {}", id);
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        productEntity.setName(productDTO.getName());
        ProductEntity saved = productRepository.save(productEntity);
        return productMapper.toDTO(saved);
    }

    /**
     * Delete product
     *
     * @param id product id
     */
    @CacheEvict(value = "products", key = "#p0")
    public void deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }

}
