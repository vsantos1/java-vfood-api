package com.ifood.service;

import com.ifood.dto.ProductDTO;
import com.ifood.mapper.Mapper;
import com.ifood.model.Product;
import com.ifood.model.Restaurant;
import com.ifood.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService  {

    private final ProductRepository productRepository;
    private final RestaurantService restaurantService;

    public ProductService(ProductRepository productRepository, RestaurantService restaurantService) {
        this.productRepository = productRepository;
        this.restaurantService = restaurantService;
    }

    public Page<Product> findAllPaginated(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findById(UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this given id");
        }
        return optionalProduct.get();
    }

    public Product execute(ProductDTO productDTO) {
        Restaurant restaurant = restaurantService.findById(productDTO.getRestaurant().getId());

        Product product = new Product();
        productDTO.setRestaurant(restaurant);
        Mapper.copyProperties(productDTO, product);

        return productRepository.save(product);
    }

    public Product update(UUID id, ProductDTO productDTO) {
        Product product = this.findById(id);

        Mapper.copyProperties(productDTO, product);

        return productRepository.save(product);
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

}
