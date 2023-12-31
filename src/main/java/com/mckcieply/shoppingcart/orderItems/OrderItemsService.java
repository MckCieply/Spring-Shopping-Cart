package com.mckcieply.shoppingcart.orderItems;

import com.mckcieply.shoppingcart.item.Item;
import com.mckcieply.shoppingcart.item.ItemRepository;
import com.mckcieply.shoppingcart.item.ItemService;
import com.mckcieply.shoppingcart.orders.Order;
import com.mckcieply.shoppingcart.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderService orderService;

    public void saveAllOrderItems(HashMap<Long, Integer> boughtItems, Order order) {
        //Saving if quantity is not 0
        for (Long itemId : boughtItems.keySet()) {
            if(boughtItems.get(itemId) != 0) {
                Item item = itemRepository.findById(itemId).get();
                orderItemsRepository.save(new OrderItems(order, item, boughtItems.get(itemId)));
            }
        }
    }

    public List<OrderItems> getAll() {
        return orderItemsRepository.findAll();
    }

    public List<String> getAllOrderItems(long id){
        List<OrderItems> orderItems = orderItemsRepository.findAllByOrderId(id);
        List<String> order = new ArrayList<>();
        System.out.println(orderItems);
        //Order id
        order.add(orderItems.get(0).getOrder().getId().toString());

        //Items in order
        for (OrderItems orderItem : orderItems) {
            order.add(orderItem.getItem().getName());
        }

        //Date of order
        String date = orderItems.get(0).getOrder().getDate_created().toString();
        date = date.substring(0, date.indexOf("."));
        order.add(date);

        //Total price
        order.add(orderItems.get(0).getOrder().getTotal_price().toString());



        return order;
    }

    public List<List> createListOfOrders(){
        List<List> allOrders = new ArrayList<>();
        List<Long> ids = orderService.getAllId();

        for(Long id: ids){
            allOrders.add(getAllOrderItems(id));
        }

        return allOrders;
    }


}
