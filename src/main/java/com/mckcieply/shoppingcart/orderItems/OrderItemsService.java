package com.mckcieply.shoppingcart.orderItems;

import com.mckcieply.shoppingcart.item.Item;
import com.mckcieply.shoppingcart.item.ItemRepository;
import com.mckcieply.shoppingcart.orders.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class OrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ItemRepository itemRepository;

    public void saveAllOrderItems(HashMap<Long, Integer> boughtItems, Order order) {
        for (Long itemId : boughtItems.keySet()) {
            if(boughtItems.get(itemId) != 0) {
                Item item = itemRepository.findById(itemId).get();
                orderItemsRepository.save(new OrderItems(order, item, boughtItems.get(itemId)));
            }

        }
    }
}
