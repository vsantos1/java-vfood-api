package com.ifood.service;

import com.ifood.dto.RestaurantDTO;
import com.ifood.mapper.Mapper;
import com.ifood.model.Kitchen;
import com.ifood.model.Restaurant;
import com.ifood.repository.KitchenRepository;
import com.ifood.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final KitchenService kitchenService;


    public RestaurantService(RestaurantRepository restaurantRepository, KitchenService kitchenService) {
        this.restaurantRepository = restaurantRepository;
        this.kitchenService = kitchenService;
    }

    public Page<Restaurant> listAllPaginated(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    public Restaurant execute(RestaurantDTO restaurantDTO) {

        Kitchen kitchen = this.kitchenService.findById(restaurantDTO.getKitchen().getId());

        restaurantDTO.setCreatedAt(new Date());
        restaurantDTO.setUpdatedAt(new Date());

        Restaurant restaurant = new Restaurant();

        restaurant.setKitchen(kitchen);

        Mapper.copyProperties(restaurantDTO, restaurant);

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
        restaurantDTO.setUpdatedAt(new Date());
        Mapper.copyProperties(restaurantDTO, restaurant);
        return restaurantRepository.save(restaurant);

    }

    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }
}
