package com.ifood.service;

import com.ifood.dto.OrderDTO;
import com.ifood.mapper.Mapper;
import com.ifood.model.Order;
import com.ifood.model.Restaurant;
import com.ifood.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;

    OrderService(OrderRepository orderRepository, RestaurantService restaurantService) {
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
    }

    public Order execute(Long restaurantId, OrderDTO orderDTO) {

        Restaurant restaurant = this.restaurantService.findById(restaurantId);

        Order order = new Order();

        Mapper.copyProperties(orderDTO, order);

        order.setCreatedAt(new Date());
        order.setRestaurant(restaurant);

        return orderRepository.save(order);

    }

    public Page<Order> findAllPaginated(Pageable pageable) {

        return orderRepository.findAll(pageable);
    }

    public Page<Order> findAllByRestaurantId(Pageable pageable, Long restaurantId) {

        return orderRepository.findOrdersByRestaurantId(pageable, restaurantId);
    }

    public Order findById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();

        }

        throw new ResourceNotFoundException("No records found for this given id");
    }

    public Order update(Long id, OrderDTO orderDTO) {
        Order order = this.findById(id);

        Mapper.copyProperties(orderDTO, order);

        return orderRepository.save(order);
    }

    public void delete(Long id) {
        Order order = this.findById(id);
        orderRepository.deleteById(order.getId());

    }
}
