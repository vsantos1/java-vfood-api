package com.ifood.resource;

import com.ifood.model.Order;
import com.ifood.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
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
}
