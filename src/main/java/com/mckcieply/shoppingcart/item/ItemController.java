package com.mckcieply.shoppingcart.item;

import com.mckcieply.shoppingcart.orderItems.OrderItemsService;
import com.mckcieply.shoppingcart.orders.Order;
import com.mckcieply.shoppingcart.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemsService orderItemsService;

    @RequestMapping(value = "/")
    public String cart() {
        return "index";
    }

    @RequestMapping(value = "/addItem")
    public String addItem(Model model) {
        model.addAttribute("item", new Item());
        return "addItem";
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute Item item) {
        itemService.saveItem(item);
        return "addItem";
    }

    @RequestMapping(value = "/cart")
    public String cart(Model model) {
        List<Item> items = itemService.getAll();
        model.addAttribute("itemList", items);
        model.addAttribute("item", new Item());
        model.addAttribute("length", itemService.getLength());
        return "cart";
    }

    @PostMapping("/success")
    public String boughtFromCart(@RequestBody String item, Model model) {
        List<Item> itemsInStock = itemService.getAll();
        List<Integer> quantityBought = itemService.itemsQuantityBought(item);
        HashMap<Long, Integer> itemsBought = itemService.boughtHashMap(itemsInStock, quantityBought);
        itemService.changeQuantity(itemsBought, itemsInStock);
        double price = itemService.calculatePrice(itemsBought);
        model.addAttribute("price", price);

        // Saving order and order items to database
        Order submitedOrder = new Order(price, new Date());
        orderService.saveOrder(submitedOrder);
        orderItemsService.saveAllOrderItems(itemsBought, submitedOrder);

        return "success";
    }

    @GetMapping("/submittedOrders")
    public String submittedOrders(Model model){
        List<List> orders = orderItemsService.createListOfOrders();
        model.addAttribute("orders", orders);
        return "submittedOrders";

    }

    @GetMapping("/updateItem")
    public String updateItem(@ModelAttribute Item item, Model model){
        List<Item> items = itemService.getAll();
        model.addAttribute("itemList", items);
        model.addAttribute("item", new Item());
        return "updateItem";
    }
}