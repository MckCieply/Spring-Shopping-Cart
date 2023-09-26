package com.mckcieply.shoppingcart.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;


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

    @RequestMapping(value = "/")
    public String index(Model model) {
        List<Item> items = itemService.getAll();
        model.addAttribute("itemList", items);
        model.addAttribute("item", new Item());
        model.addAttribute("length", itemService.getLength());
        return "index";
    }

    @PostMapping("/success")
    public String boughtFromCart(@RequestBody String item, Model model) {
        List<Item> itemsInStock = itemService.getAll();
        List<Integer> quantityBought = itemService.itemsQuantityBought(item);
        HashMap<Long, Integer> itemsBought = itemService.boughtHashMap(itemsInStock, quantityBought);
        itemService.changeQuantity(itemsBought, itemsInStock);
        double price = itemService.calculatePrice(itemsBought);
        model.addAttribute("price", price);
        return "success";
    }
}
