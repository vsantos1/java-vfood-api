package com.ifood.resource;

import com.ifood.dto.OrderDTO;
import com.ifood.model.Order;
import com.ifood.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Order", description = "Order endpoint API")
public class OrderResource {

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Order>> getAllPaginated(@PageableDefault(size = 10, value = 10, page = 0) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllPaginated(pageable));
    }

    @GetMapping(value = "/orders/restaurants/{restaurant_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Order>> getAllOrdersByRestaurantId(@PathVariable("restaurant_id") Long id, @PageableDefault Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllByRestaurantId(pageable, id));
    }

    @GetMapping(value = "/orders/{order_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getById(@PathVariable("order_id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
    }

    @PostMapping(value = "/orders/restaurants/{restaurant_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> create(@PathVariable("restaurant_id") Long restaurantId, @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.execute(restaurantId, orderDTO));
    }

    @PutMapping(value = "/orders/{order_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> update(@PathVariable("order_id") Long id, @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(id, orderDTO));
    }

    @DeleteMapping(value = "/orders/{order_id}")
    public ResponseEntity<Void> delete(@PathVariable("order_id") Long id) {
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
