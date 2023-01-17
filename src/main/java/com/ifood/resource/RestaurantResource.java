package com.ifood.resource;

import com.ifood.dto.RestaurantDTO;
import com.ifood.model.Restaurant;
import com.ifood.service.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")
public class RestaurantResource {

    private final RestaurantService restaurantService;

    public RestaurantResource(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Restaurant>> getAllPaginated(@PageableDefault(size = 10, page = 0, value = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.listAllPaginated(pageable));
    }

    @GetMapping(value = "/restaurants/{restaurant_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> getById(@PathVariable("restaurant_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findById(id));
    }

    @PostMapping(value = "/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody @Valid RestaurantDTO restaurantDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.execute(restaurantDTO));
    }

    @PutMapping(value = "/restaurants/{restaurant_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> update(@PathVariable("restaurant_id") Long id, @RequestBody @Valid RestaurantDTO restaurantDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.update(id, restaurantDTO));
    }

    @DeleteMapping(value = "/restaurants/{restaurant_id}")
    public ResponseEntity<Void> delete(@PathVariable("restaurant_id") Long id) {
        restaurantService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
