package com.ifood.service;

import com.ifood.dto.OrderDTO;
import com.ifood.mapper.Mapper;
import com.ifood.model.Order;
import com.ifood.repository.OrderRepository;
import org.springframework.stereotype.Service;

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
}
