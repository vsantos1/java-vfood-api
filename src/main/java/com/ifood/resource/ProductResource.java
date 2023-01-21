package com.ifood.resource;

import com.ifood.dto.ProductDTO;
import com.ifood.model.Product;
import com.ifood.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
@Tag(name = "Product", description = "Product endpoint API")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Product>> getAllPaginated(@PageableDefault(size = 10, page = 0, value = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllPaginated(pageable));
    }


    @GetMapping(value = "/products/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getById(@PathVariable("product_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> create(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.execute(productDTO));
    }

    @PutMapping(value = "/products/{product_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> update(@PathVariable("product_id") Long id, @RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, productDTO));
    }

    @DeleteMapping(value = "/products/{product_id}")
    public ResponseEntity<Void> delete(@PathVariable("product_id") Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
