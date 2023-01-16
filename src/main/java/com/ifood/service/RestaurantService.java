package com.ifood.service;

import com.ifood.dto.RestaurantDTO;
import com.ifood.model.Restaurant;
import com.ifood.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    public final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Page<Restaurant> listAllPaginated(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    public Restaurant execute(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(restaurantDTO, restaurant);

        return restaurantRepository.save(restaurant);
    }

    public Restaurant findById(Long id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            return optionalRestaurant.get();
        }
        throw new ResourceNotFoundException("No records found for this given id");
    }

    public Restaurant update(Long id, RestaurantDTO restaurantDTO) {
        Restaurant restaurant = this.findById(id);

        BeanUtils.copyProperties(restaurantDTO, restaurant, "id");

        return restaurantRepository.save(restaurant);

    }

    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }
}
