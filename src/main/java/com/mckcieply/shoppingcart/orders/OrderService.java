package com.mckcieply.shoppingcart.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    //Extracting IDs from orders
    public List<Long> getAllId() {
        List<Order> orders = orderRepository.findAll();
        List<Long> ids = new ArrayList<>();

        for (Order order : orders) {
            ids.add(order.getId());
        }
        return ids;
    }
}
