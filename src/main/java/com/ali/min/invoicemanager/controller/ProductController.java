package com.ali.min.invoicemanager.controller;

import com.ali.min.invoicemanager.dto.ProductDTO;
import com.ali.min.invoicemanager.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling product related operations
 *
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product")
@SecurityRequirement(name = "inv-man-auth")
public class ProductController {

    private final ProductService productService;

    /**
     * Get all products
     *
     * @return List of products
     */
    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    /**
     * Get product by id
     *
     * @param id product id
     * @return product
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get product by id")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    /**
     * Save product
     *
     * @param productDTO product to be saved
     * @return saved product
     */
    @PostMapping
    @Operation(summary = "Save product")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.saveProduct(productDTO));
    }

    /**
     * Update product
     *
     * @param id product id
     * @param productDTO product to be updated
     * @return updated product
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update product")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    /**
     * Delete product
     *
     * @param id product id
     * @return void
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
