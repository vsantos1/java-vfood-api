package com.ifood.repository;

import com.ifood.model.Image;
import com.ifood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByRestaurantId(Long id);

    Optional<Product> findProductByImage_Url(String url);
}
