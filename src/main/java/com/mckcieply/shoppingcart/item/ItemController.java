package com.mckcieply.shoppingcart.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    public String index (Model model) {
        List<Item> items = itemService.getAll();
        model.addAttribute("itemList", items);
        model.addAttribute("item", new Item());
        model.addAttribute("length", itemService.getLength());
        return "index";
    }

    @PostMapping("/success")
    public String boughtFromCart(@RequestBody String item) {
        List<Item> oldItems = itemService.getAll();
        ArrayList<String> items = new ArrayList<String>(
                Arrays.asList(item.split("&")));
        itemService.changeQuantity(oldItems, items);
        return "success";
    }

}
