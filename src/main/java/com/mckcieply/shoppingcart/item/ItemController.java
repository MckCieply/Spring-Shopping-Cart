package com.mckcieply.shoppingcart.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        model.addAttribute("length", itemService.getLength());
        return "index";
    }

    @PostMapping("/success")
    public String boughtFromCart(){

        return "success";
    }

}
