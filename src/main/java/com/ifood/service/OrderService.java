package com.ifood.service;

import com.ifood.dto.OrderDTO;
import com.ifood.mapper.Mapper;
import com.ifood.model.Order;
import com.ifood.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;


    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(OrderDTO orderDTO) {

        Order order = new Order();

        Mapper.copyProperties(orderDTO, order);


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
}
