package com.ali.min.invoicemanager.service;

import com.ali.min.invoicemanager.dto.ProductDTO;
import com.ali.min.invoicemanager.entity.ProductEntity;
import com.ali.min.invoicemanager.mapper.ProductMapper;
import com.ali.min.invoicemanager.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private ProductDTO productDTO;
    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productDTO = new ProductDTO();
        productDTO.setName("Test Product");

        productEntity = new ProductEntity();
        productEntity.setName("Test Product");
    }

    @Test
    void getProducts_returnsAllProducts() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(productEntity));
        when(productMapper.toDTO(any(ProductEntity.class))).thenReturn(productDTO);

        List<ProductDTO> result = productService.getProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(productDTO.getName(), result.get(0).getName());
        verify(productRepository).findAll();
    }

    @Test
    void getProduct_returnsProductById() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(productEntity));
        when(productMapper.toDTO(any(ProductEntity.class))).thenReturn(productDTO);

        ProductDTO result = productService.getProduct(1L);

        assertNotNull(result);
        assertEquals(productDTO.getName(), result.getName());
        verify(productRepository).findById(1L);
    }

    @Test
    void getProduct_throwsExceptionWhenProductNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.getProduct(1L));
    }

    @Test
    void saveProduct_savesProductSuccessfully() {
        when(productMapper.toEntity(any(ProductDTO.class))).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.toDTO(any(ProductEntity.class))).thenReturn(productDTO);

        ProductDTO result = productService.saveProduct(productDTO);

        assertNotNull(result);
        assertEquals(productDTO.getName(), result.getName());
        verify(productRepository).save(productEntity);
    }

    @Test
    void updateProduct_updatesProductSuccessfully() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(productEntity));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.toDTO(any(ProductEntity.class))).thenReturn(productDTO);

        ProductDTO result = productService.updateProduct(1L, productDTO);

        assertNotNull(result);
        assertEquals(productDTO.getName(), result.getName());
        verify(productRepository).findById(1L);
        verify(productRepository).save(productEntity);
    }
    @Test
    void updateProduct_throwsExceptionWhenProductNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(1L, productDTO));
    }

    @Test
    void deleteProduct_deletesProductSuccessfully() {
        productService.deleteProduct(1L);

        verify(productRepository).deleteById(1L);
    }
}